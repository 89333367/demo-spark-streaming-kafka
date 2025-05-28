package sunyu.demo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import kafka.common.TopicAndPartition;
import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.HasOffsetRanges;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.kafka.OffsetRange;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import relocation.org.apache.kafka.clients.consumer.OffsetAndMetadata;
import relocation.org.apache.kafka.common.TopicPartition;
import sunyu.demo.common.BsrDatas;
import sunyu.demo.entity.TwNrvRedundant;
import sunyu.util.KafkaOffsetUtil;
import sunyu.util.KafkaProducerUtil;
import uml.tech.bigdata.sdkconfig.ProtocolSdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static Log log = LogFactory.get();
    static Props props = new Props("application.properties");
    static KafkaOffsetUtil kafkaOffsetUtil = KafkaOffsetUtil.builder().bootstrapServers(props.getStr("kafka")).groupId(props.getStr("kafka.group.id")).topic(props.getStr("kafka.topics")).build();
    static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring*.xml");
    static BsrDatas bsrDatas = applicationContext.getBean(BsrDatas.class);
    static KafkaProducerUtil kafkaProducerUtil = KafkaProducerUtil.builder().bootstrapServers(props.getStr("kafka.producer")).build();
    static ProtocolSdk protocolSdk = applicationContext.getBean(ProtocolSdk.class);
    static String producerTopic = props.getStr("kafka.producer.topic");

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
                    Map<String, TwNrvRedundant> redundantMap = bsrDatas.getRedundantMap();
                    log.info("钵施然数量 {}", redundantMap.size());

                    javaRDD.filter((Function<String[], Boolean>) v1 -> {
                        String did = v1[1];
                        return StrUtil.isNotBlank(did) && redundantMap.containsKey(did);
                    }).foreachPartition((VoidFunction<Iterator<String[]>>) iterator -> {
                        iterator.forEachRemaining(strings -> {
                            String did = strings[1];
                            String protocol = strings[2];
                            TwNrvRedundant twNrvRedundant = redundantMap.get(did);
                            TreeMap<String, String> tm = protocolSdk.parseProtocolString(protocol);
                            if (MapUtil.isNotEmpty(tm)) {
                                Map<String, Object> msg = new HashMap<>();
                                msg.put("vin", twNrvRedundant.getVin());
                                msg.put("vehicleModel", twNrvRedundant.getVehicleModel());
                                msg.put("did", twNrvRedundant.getDid());
                                msg.put("imei", twNrvRedundant.getImei());
                                msg.put("orgNamePath", twNrvRedundant.getOrgNamePath());
                                msg.put("5341", tm.get("5341"));
                                msg.put("3040", tm.get("3040"));
                                msg.put("2948", tm.get("2948"));
                                msg.put("5350", tm.get("5350"));
                                msg.put("5353", tm.get("5353"));
                                msg.put("5354", tm.get("5354"));
                                msg.put("5356", tm.get("5356"));
                                msg.put("5357", tm.get("5357"));
                                msg.put("3006", tm.get("3006"));
                                msg.put("4810", tm.get("4810"));
                                msg.put("2602", tm.get("2602"));
                                msg.put("2603", tm.get("2603"));
                                msg.put("2205", tm.get("2205"));
                                msg.put("2204", tm.get("2204"));
                                msg.put("2201", tm.get("2201"));
                                msg.put("3014", tm.get("3014"));
                                msg.put("4119", tm.get("4119"));
                                String message = JSONUtil.toJsonStr(msg);
                                log.info("准备发送消息 {}", message);
                                kafkaProducerUtil.send(producerTopic, twNrvRedundant.getDid(), message);
                            }
                        });
                        kafkaProducerUtil.flush();
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