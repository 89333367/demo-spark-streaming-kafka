package sunyu.demo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import kafka.common.TopicAndPartition;
import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.HasOffsetRanges;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.kafka.OffsetRange;
import relocation.org.apache.kafka.clients.consumer.OffsetAndMetadata;
import relocation.org.apache.kafka.common.TopicPartition;
import sunyu.util.KafkaOffsetUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    static Log log = LogFactory.get();
    static Props props = new Props("application.properties");
    static KafkaOffsetUtil kafkaOffsetUtil = KafkaOffsetUtil.builder().bootstrapServers(props.getStr("kafka")).groupId(props.getStr("kafka.group.id")).topic(props.getStr("kafka.topics")).build();

    public static void main(String[] args) {
        long seconds = 30;

        Map<String, String> kafkaParams = kafkaOffsetUtil.getKafkaParamsMap();
        log.info("[kafka参数] {}", kafkaParams);

        SparkConf sparkConf = new SparkConf();
        if (ArrayUtil.isEmpty(args)) {
            sparkConf.setAppName("spark streaming test");
            sparkConf.setMaster("local[*]");
            sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "10");
        } else {
            log.info("命令行参数 : {}", JSONUtil.toJsonStr(args));
            seconds = Convert.toLong(args[0]);
        }

        Duration batchDuration = Durations.seconds(seconds);
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, batchDuration);

        // 修复offsets
        kafkaOffsetUtil.fixCurrentOffsets();
        //获得当前的偏移量
        Map<TopicAndPartition, Long> fromOffsets = new HashMap<>();
        Map<TopicPartition, Long> curOffsets = kafkaOffsetUtil.getCurrentOffsets();
        log.info("[curOffsets] {}", curOffsets);
        curOffsets.forEach((topicPartition, aLong) -> fromOffsets.put(new TopicAndPartition(topicPartition.topic(), topicPartition.partition()), aLong));
        log.info("[fromOffsets] {}", fromOffsets);

        KafkaUtils.createDirectStream(javaStreamingContext, String.class, String.class,
                        StringDecoder.class, StringDecoder.class, String[].class, kafkaParams, fromOffsets,
                        v1 -> new String[]{v1.topic(), v1.key(), v1.message()})
                .foreachRDD((VoidFunction<JavaRDD<String[]>>) javaRDD -> {
                    //循环一批数据
                    javaRDD.foreachPartition((VoidFunction<Iterator<String[]>>) iterator -> {
                        iterator.forEachRemaining(strings -> {
                            log.info("[收到数据] topic:{} key:{} value:{}", strings[0], strings[1], strings[2]);
                            // todo 处理数据
                        });
                    });

                    // todo 提交offset
                    OffsetRange[] offsetRanges = ((HasOffsetRanges) javaRDD.rdd()).offsetRanges();
                    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                    for (OffsetRange offsetRange : offsetRanges) {
                        offsets.put(new TopicPartition(offsetRange.topic(), offsetRange.partition()), new OffsetAndMetadata(offsetRange.untilOffset()));
                    }
                    kafkaOffsetUtil.commitOffsets(offsets);
                });

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }
}