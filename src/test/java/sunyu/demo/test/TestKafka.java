package sunyu.demo.test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import org.junit.jupiter.api.Test;
import relocation.org.apache.kafka.clients.consumer.ConsumerRecord;
import relocation.org.apache.kafka.common.TopicPartition;
import sunyu.util.KafkaConsumerUtil;
import sunyu.util.KafkaOffsetUtil;

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

    public static void main(String[] args) {
        Props props = new Props("application.properties");
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
