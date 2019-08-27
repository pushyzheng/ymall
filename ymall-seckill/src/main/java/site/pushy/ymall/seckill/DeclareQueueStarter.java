package site.pushy.ymall.seckill;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import site.pushy.ymall.seckill.listener.SeckillMessageListener;

import static site.pushy.ymall.common.config.RabbitConstants.*;
import static site.pushy.ymall.common.config.RabbitConstants.AUTO_DELETE;

/**
 * @author pushyzheng
 * @since 2019/8/26
 */
@Component
public class DeclareQueueStarter implements ApplicationRunner {

    @Autowired
    private Channel channel;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Starting declare RabbitMQ queue ...");

        // 声明和消费队列
        channel.queueDeclare(QUEUE_SECKILL_SAVE_ORDER,
                DURABLE, EXCLUSIVE, AUTO_DELETE, null);
        channel.basicConsume(QUEUE_SECKILL_SAVE_ORDER, false, new SeckillMessageListener(), consumerTag -> {
            System.out.println(consumerTag);
        });
    }
}
