package sunyu.demo.test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.jupiter.api.Test;
import sunyu.util.KafkaOffsetUtil;

public class TestKafka {
    Log log = LogFactory.get();

    @Test
    void t001() {
        KafkaOffsetUtil kafkaOffsetUtil = KafkaOffsetUtil.builder()
                .bootstrapServers("172.16.1.5:9092,172.16.1.15:9092,172.16.1.16:9092")
                .groupId("bsr_stream_mq_prod_test")
                .topic("US_GENERAL_NJ")
                .build();

        log.info("{}", kafkaOffsetUtil.getCurrentOffsets());

        //程序关闭前回收资源
        kafkaOffsetUtil.close();
    }
}
