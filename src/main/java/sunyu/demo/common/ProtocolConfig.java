package sunyu.demo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uml.tech.bigdata.sdkconfig.ProtocolSdk;

@Configuration
public class ProtocolConfig {

    @Value("${config.url}")
    String url;
    @Value("${config.cron}")
    String cron;

    @Bean
    public ProtocolSdk protocolSdk() {
        return new ProtocolSdk(url, cron);
    }
}
