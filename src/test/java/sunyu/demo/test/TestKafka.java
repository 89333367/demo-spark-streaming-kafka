package sunyu.demo.test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import org.junit.Test;
import sunyu.util.KafkaOffsetUtil;

public class TestKafka {
    Log log = LogFactory.get();

    @Test
    public void t001() {
        Props props = new Props("application.properties");
        KafkaOffsetUtil kafkaOffsetUtil = KafkaOffsetUtil.builder()
                .bootstrapServers(props.getStr("kafka"))
                .groupId(props.getStr("kafka.group.id"))
                .topic(props.getStr("kafka.topics"))
                .build();
        log.info("{}", kafkaOffsetUtil.offsetEarliest());
        log.info("{}", kafkaOffsetUtil.offsetCurrent());
        log.info("{}", kafkaOffsetUtil.offsetLatest());
        kafkaOffsetUtil.close();
    }
}
