package site.pushy.ymall.web.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Channel connectionFactory() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.165.238");
        factory.setUsername("Pushy");
        factory.setPassword("123456");

        Connection conn = factory.newConnection();
        return conn.createChannel();
    }

}
