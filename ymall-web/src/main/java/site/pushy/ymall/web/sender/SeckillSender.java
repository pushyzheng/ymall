package site.pushy.ymall.web.sender;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.pushy.ymall.common.config.RabbitConstants;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.util.MD5Util;
import site.pushy.ymall.dao.TbUserMapper;
import site.pushy.ymall.pojo.dto.SeckillOrderInfo;
import site.pushy.ymall.pojo.dto.SeckillSaveOrderDTO;
import site.pushy.ymall.pojo.dto.SeckillTask;

import java.io.IOException;

import static site.pushy.ymall.common.config.RedisPrefix.*;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
@Component
public class SeckillSender {

    private static final Logger logger = LoggerFactory.getLogger(SeckillSender.class);

    @Autowired
    private JedisClient redis;

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private Channel channel;

    public void saveOrder(String seckillId, SeckillOrderInfo orderInfo) {
        Long userId = Long.valueOf(orderInfo.getUserId());
        if (userId == 0L || userMapper.selectByPrimaryKey(userId) == null) {
            throw new HttpErrorException("用户不存在");
        }

        // 防止用户重复提交订单
        String identity = seckillId + orderInfo.getUserId();
        String key = SECKILL_ORDER_LOCK_KEY + ":" + MD5Util.getMD5(identity);
        if (redis.setnx(key, "") == FALSE) {
            throw new HttpErrorException("短时间内请勿重复提交订单");
        }
        redis.expire(key, 5);

        // 将给前端轮询查询秒杀状态的数据预存到redis，降低查询数据库订单的压力
        // 异步服务器收到秒杀任务后，会修改这些标识数据
        SeckillTask task = new SeckillTask();
        redis.hset(getSeckillTaskKey(orderInfo.getItemId()), orderInfo.getUserId(), JSON.toJSONString(task));

        // 发送消息队列给秒杀服务器处理订单请求
        SeckillSaveOrderDTO data = new SeckillSaveOrderDTO();
        data.setSeckillId(seckillId);
        data.setOrderInfo(orderInfo);
        String json = JSON.toJSONString(data);

        logger.info("send to seckill => " + json);

        try {
            channel.basicPublish("", RabbitConstants.QUEUE_SECKILL_SAVE_ORDER,
                    MessageProperties.PERSISTENT_BASIC, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
