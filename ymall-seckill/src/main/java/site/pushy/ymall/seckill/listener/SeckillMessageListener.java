package site.pushy.ymall.seckill.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import site.pushy.ymall.common.config.RedisPrefix;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.util.MD5Util;
import site.pushy.ymall.pojo.dto.Seckill;
import site.pushy.ymall.pojo.dto.SeckillSaveOrderDTO;
import site.pushy.ymall.pojo.dto.SeckillTask;
import site.pushy.ymall.service.SeckillService;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static site.pushy.ymall.common.config.RedisPrefix.*;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
public class SeckillMessageListener implements DeliverCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private JedisClient redis;

    @Autowired
    private Channel channel;

    private Set<Long> deliverTagSet = new HashSet<>();

    @Override
    public void handle(String consumerTag, Delivery message) throws IOException {
        long deliverTag = message.getEnvelope().getDeliveryTag();
        // 避免相同的消息被重复消费了
        if (deliverTagSet.contains(deliverTag)) {
            return;
        }
        deliverTagSet.add(deliverTag);

        String json = new String(message.getBody());
        SeckillSaveOrderDTO data = JSON.parseObject(json, SeckillSaveOrderDTO.class);  // web异步任务

        // 避免相同数据被重复消费了
        Seckill seckill = JSON.parseObject(redis.get(SECKILL_PRE + ":" + data.getOrderInfo().getItemId()), Seckill.class);
        if (seckill == null) {
            return;
        }
        String hash = MD5Util.getMD5(data.getOrderInfo().getItemId() + "" + data.getOrderInfo().getUserId());
        if (redis.lgetAll(QUEUE_IDEMPOTENT).contains(hash)) {
            return;
        }
        redis.lpush(QUEUE_IDEMPOTENT, hash);
        int remainTime = (int) ((seckill.getEndTime().getTime() - System.currentTimeMillis()) / 1000); // 秒杀剩余的秒数
        redis.expire(QUEUE_IDEMPOTENT, remainTime);

        String taskKey = getSeckillTaskKey(data.getOrderInfo().getItemId());
        json = redis.hget(taskKey, data.getOrderInfo().getUserId());
        SeckillTask seckillTask = JSON.parseObject(json, SeckillTask.class);           // 任务信息，供前端轮询查询

        try {
            seckillService.saveOrder(data.getSeckillId(), data.getOrderInfo());
            seckillTask.setSucceed(true);
            logger.info("秒杀成功： " + data.getOrderInfo().getUserId());
        } catch (HttpErrorException e) {
            seckillTask.setSucceed(false);
            seckillTask.setErrMessage(e.getMessage());
            logger.info("秒杀失败： " + data.getOrderInfo().getUserId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            seckillTask.setSucceed(false);
            seckillTask.setErrMessage(e.getMessage());
        }

        // 确认消息成功消费
        channel.basicAck(deliverTag, false);
        // 将 seckillTask 重写进redis
        seckillTask.setFinished(true);
        redis.hset(taskKey, data.getOrderInfo().getUserId(), JSON.toJSONString(seckillTask));
        redis.expire(taskKey, 120);  // 两分钟之内删除该数据
    }

}
