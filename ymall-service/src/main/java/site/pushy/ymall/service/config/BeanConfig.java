package site.pushy.ymall.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.jedis.JedisClientPool;

@Configuration
public class BeanConfig {

    @Bean
    public JedisClient jedisClient() {
        return new JedisClientPool();
    }

}
