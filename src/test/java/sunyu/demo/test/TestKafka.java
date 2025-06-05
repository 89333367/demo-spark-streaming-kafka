package sunyu.demo.test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import org.junit.jupiter.api.Test;
import relocation.org.apache.kafka.clients.consumer.ConsumerRecord;
import relocation.org.apache.kafka.common.TopicPartition;
import sunyu.util.KafkaConsumerUtil;
import sunyu.util.KafkaOffsetUtil;
import sunyu.util.KafkaProducerUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class TestKafka {
    static Log log = LogFactory.get();

    @Test
    public void t001() {
        Props props = new Props("application.properties");
        KafkaOffsetUtil kafkaOffsetUtil = KafkaOffsetUtil.builder()
                .bootstrapServers(props.getStr("kafka"))
                .groupId(props.getStr("kafka.group.id"))
                .topic(props.getStr("kafka.topics"))
                .build();

        for (TopicPartition topicPartition : kafkaOffsetUtil.getTopicPartitions()) {
            log.info("{}", kafkaOffsetUtil.getEarliestOffset(topicPartition));
            log.info("{}", kafkaOffsetUtil.getCurrentOffset(topicPartition));
            log.info("{}", kafkaOffsetUtil.getLatestOffset(topicPartition));
        }

        kafkaOffsetUtil.close();
    }

    @Test
    public void t002() throws ExecutionException, InterruptedException, TimeoutException {
        Props props = new Props("application.properties");
        log.info("{} {} {}", props.getStr("kafka.producer"), props.getStr("kafka.group.id"), props.getStr("kafka.producer.topic"));
        KafkaProducerUtil kafkaProducerUtil = KafkaProducerUtil.builder()
                .bootstrapServers(props.getStr("kafka.producer"))
                .build();

        String did = "BSR3152412100004";
        String value = "{\"orgNamePath\":\"钵施然农机#\",\"5350\":\"2479392016\",\"5341\":\"11\",\"5353\":\"0\",\"5354\":\"0\",\"5356\":\"0\",\"3040\":\"0.0\",\"2603\":\"43.946445\",\"2602\":\"87.552993\",\"2205\":\"0\",\"2204\":\"0.0\",\"3006\":\"20.29\",\"2201\":\"0\",\"3014\":\"20250605100300\",\"5357\":\"36648\",\"vehicleModel\":\"4MZD-6C\",\"imei\":\"862965070422085\",\"vin\":\"WL793925032016\",\"4810\":\"0\",\"2948\":\"1\",\"4119\":\"0\",\"did\":\"BSR3152412100004\"}";

        kafkaProducerUtil.send(props.getStr("kafka.producer.topic"), did, value).get(10, TimeUnit.SECONDS);
        kafkaProducerUtil.flush();

        kafkaProducerUtil.close();
    }

    public static void main(String[] args) {
        Props props = new Props("application.properties");
        log.info("{} {} {}", props.getStr("kafka.producer"), props.getStr("kafka.group.id"), props.getStr("kafka.producer.topic"));
        KafkaConsumerUtil kafkaConsumerUtil = KafkaConsumerUtil.builder()
                .bootstrapServers(props.getStr("kafka.producer"))
                .groupId(props.getStr("kafka.group.id"))
                .topic(props.getStr("kafka.producer.topic"))
                .build();
        kafkaConsumerUtil.pollRecord(new Consumer<ConsumerRecord<String, String>>() {
            @Override
            public void accept(ConsumerRecord<String, String> stringStringConsumerRecord) {
                log.info("{}", stringStringConsumerRecord);
            }
        });
    }
}
