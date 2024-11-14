package sunyu.demo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import kafka.common.TopicAndPartition;
import kafka.serializer.StringDecoder;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.HasOffsetRanges;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.kafka.OffsetRange;
import scala.Tuple2;

import java.util.*;

public class Main {
    static Log log = LogFactory.get();
    static Props props = new Props("application.properties");

    public static void main(String[] args) {
        long seconds = 10;

        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getStr("kafka"));
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, props.getStr("kafka.group.id"));
        kafkaParams.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 由于设置这个会报错 Wrong value earliest of auto.offset.reset in ConsumerConfig; Valid values are smallest and largest ，所以这里不设置也可以
        //kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, props.getStr("kafka.offset.reset").toLowerCase());// earliest latest

        SparkConf sparkConf = new SparkConf();
        if (ArrayUtil.isEmpty(args)) {
            sparkConf.setAppName("spark streaming test");
            sparkConf.setMaster("local[*]");
            sparkConf.set("spark.streaming.kafka.maxRatePerPartition", "100");
        } else {
            log.info("命令行参数 : {}", JSONUtil.toJsonStr(args));
            seconds = Convert.toLong(args[0]);
        }

        Duration batchDuration = Durations.seconds(seconds);
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, batchDuration);

        // todo 初始化offsets
        Consumer<?, ?> kafkaConsumer = new KafkaConsumer(kafkaParams);
        List<TopicPartition> topicPartitions = new ArrayList<>();
        for (String topic : props.getStr("kafka.topics").split(",")) {
            List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);
            for (PartitionInfo partitionInfo : partitionInfos) {
                TopicPartition topicPartition = new TopicPartition(topic, partitionInfo.partition());
                topicPartitions.add(topicPartition);
            }
        }
        kafkaConsumer.assign(topicPartitions);
        Map<TopicAndPartition, Long> fromOffsets = new HashMap<>();
        Map<TopicPartition, OffsetAndMetadata> curOffsets = offsetCurrent(topicPartitions, kafkaConsumer);
        curOffsets.forEach((topicPartition, offsetAndMetadata) -> fromOffsets.put(new TopicAndPartition(topicPartition.topic(), topicPartition.partition()), offsetAndMetadata.offset()));
        log.info("fromOffsets {}", fromOffsets);

        KafkaUtils.createDirectStream(javaStreamingContext, String.class, String.class,
                        StringDecoder.class, StringDecoder.class, String[].class, kafkaParams, fromOffsets,
                        v1 -> new String[]{v1.topic(), v1.key(), v1.message()})
                .foreachRDD((VoidFunction<JavaRDD<String[]>>) javaRDD -> {
                    // todo 分区处理
                    /*javaRDD.foreachPartition((VoidFunction<Iterator<String[]>>) iterator -> {
                        // todo 数据处理
                        iterator.forEachRemaining(strings -> {
                            String topic = strings[0];
                            String key = strings[1];
                            String msg = strings[2];
                            log.debug("{} {} {}", topic, key, msg);
                        });
                    });*/

                    // todo 分区处理
                    javaRDD.filter((Function<String[], Boolean>) v1 -> {
                                //过滤设备号带1的
                                return v1[1].contains("1");
                            }).mapToPair((PairFunction<String[], String, String>) strings -> {
                                //key:设备号，value:内部协议
                                return new Tuple2<>(strings[1], strings[2]);
                            }).groupByKey()
                            .foreachPartition((VoidFunction<Iterator<Tuple2<String, Iterable<String>>>>) tuple2Iterator -> {
                                // todo 循环分区
                                tuple2Iterator.forEachRemaining(stringIterableTuple2 -> {
                                    String did = stringIterableTuple2._1;
                                    Iterable<String> datas = stringIterableTuple2._2;
                                    // todo 循环数据
                                    for (String data : datas) {
                                        // todo 处理数据
                                    }
                                });
                            });

                    // todo 提交offset
                    OffsetRange[] offsetRanges = ((HasOffsetRanges) javaRDD.rdd()).offsetRanges();
                    for (OffsetRange offsetRange : offsetRanges) {
                        log.info("{}", offsetRange);
                        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                        offsets.put(new TopicPartition(offsetRange.topic(), offsetRange.partition()), new OffsetAndMetadata(offsetRange.untilOffset()));
                        try {
                            kafkaConsumer.commitSync(offsets);
                            log.info("提交偏移量成功 {}", offsetRange);
                        } catch (Exception e) {
                            log.error("提交偏移量异常 {} {}", offsetRange, e.getMessage());
                        }
                    }
                });

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
    }


    /**
     * 获得最初的offset
     *
     * @return
     */
    public static Map<TopicPartition, OffsetAndMetadata> offsetEarliest(List<TopicPartition> topicPartitions, Consumer<?, ?> consumer) {
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        for (TopicPartition topicPartition : topicPartitions) {
            consumer.seekToBeginning(topicPartition);
            long offset = consumer.position(topicPartition);
            //log.info("EARLIEST offset {} {} {}", topic, topicPartition.partition(), offset);
            offsets.put(topicPartition, new OffsetAndMetadata(offset));
        }
        return offsets;
    }

    /**
     * 获得最后的offset
     *
     * @return
     */
    public static Map<TopicPartition, OffsetAndMetadata> offsetLatest(List<TopicPartition> topicPartitions, Consumer<?, ?> consumer) {
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        for (TopicPartition topicPartition : topicPartitions) {
            consumer.seekToEnd(topicPartition);
            long offset = consumer.position(topicPartition);
            //log.info("LATEST offset {} {} {}", topic, topicPartition.partition(), offset);
            offsets.put(topicPartition, new OffsetAndMetadata(offset));
        }
        return offsets;
    }


    /**
     * 获得当前的offset
     *
     * @return
     */
    public static Map<TopicPartition, OffsetAndMetadata> offsetCurrent(List<TopicPartition> topicPartitions, Consumer<?, ?> consumer) {
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        for (TopicPartition topicPartition : topicPartitions) {
            OffsetAndMetadata committed = consumer.committed(topicPartition);
            if (committed != null) {
                //log.info("CURRENT group offset {} {} {}", topic, topicPartition.partition(), committed.offset());
                offsets.put(topicPartition, new OffsetAndMetadata(committed.offset()));
            } else {
                if (props.getStr("kafka.offset.reset").equalsIgnoreCase(OffsetResetStrategy.LATEST.name())) {
                    consumer.seekToEnd(topicPartition);
                    long offset = consumer.position(topicPartition);
                    //log.info("LATEST offset {} {} {}", topic, topicPartition.partition(), offset);
                    offsets.put(topicPartition, new OffsetAndMetadata(offset));
                } else {
                    consumer.seekToBeginning(topicPartition);
                    long offset = consumer.position(topicPartition);
                    //log.info("EARLIEST offset {} {} {}", topic, topicPartition.partition(), offset);
                    offsets.put(topicPartition, new OffsetAndMetadata(offset));
                }
            }
        }
        // todo 修正offset，避免超出range
        Map<TopicPartition, OffsetAndMetadata> earliest = offsetEarliest(topicPartitions, consumer);
        earliest.forEach((topicPartition, offsetAndMetadata) -> {
            OffsetAndMetadata cur = offsets.get(topicPartition);
            if (cur == null) {
                offsets.put(topicPartition, offsetAndMetadata);
            } else if (cur.offset() < offsetAndMetadata.offset()) {
                log.warn("当前 {}-{} 小于偏移量范围 {} < {} 进行修正", topicPartition.topic(), topicPartition.partition(), cur.offset(), offsetAndMetadata.offset());
                offsets.put(topicPartition, offsetAndMetadata);
            }
        });
        Map<TopicPartition, OffsetAndMetadata> latest = offsetLatest(topicPartitions, consumer);
        latest.forEach((topicPartition, offsetAndMetadata) -> {
            OffsetAndMetadata cur = offsets.get(topicPartition);
            if (cur == null) {
                offsets.put(topicPartition, offsetAndMetadata);
            } else if (cur.offset() > offsetAndMetadata.offset()) {
                log.warn("当前 {}-{} 大于偏移量范围 {} > {} 进行修正", topicPartition.topic(), topicPartition.partition(), cur.offset(), offsetAndMetadata.offset());
                offsets.put(topicPartition, offsetAndMetadata);
            }
        });
        return offsets;
    }
}