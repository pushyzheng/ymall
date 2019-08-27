package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pushy.ymall.common.config.RedisPrefix;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.exception.SeckillConcurrentFailException;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.util.IDUtil;
import site.pushy.ymall.dao.*;
import site.pushy.ymall.pojo.DO.*;
import site.pushy.ymall.pojo.dto.*;
import site.pushy.ymall.service.SeckillService;

import static site.pushy.ymall.common.config.RedisPrefix.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private JedisClient redis;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断商品当前是否参与秒杀
     */
    @Override
    public Seckill isSeckillGood(Long itemId) {
        String json = redis.get(getSeckillKey(itemId));
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, Seckill.class);
    }

    @Override
    public List<SeckillDTO> listSeckill() {
        // 查询缓存
        String json = redis.get(CACHE_SECKILL_ALL);
        if (json != null) {
            return JSON.parseArray(json, SeckillDTO.class);
        }

        // 通过 keys 命令的正则去查询到所有的秒杀商品的键
        Set<String> keys = redis.keys(SECKILL_PRE + ":*");
        List<SeckillDTO> result = new ArrayList<>();
        // 去缓存里获取商品的信息
        for (String key : keys) {
            Seckill seckill = JSON.parseObject(redis.get(key), Seckill.class);
            TbItem item = itemMapper.selectByPrimaryKey(seckill.getItemId());
            result.add(new SeckillDTO(seckill, item));
        }

        // 加入缓存
        redis.set(CACHE_SECKILL_ALL, JSON.toJSONString(result));
        return result;
    }

    @Override
    public SeckillTask getSeckillTask(Long userId, Long itemId) {
        String json = redis.hget(getSeckillTaskKey(itemId), userId.toString());
        return JSON.parseObject(json, SeckillTask.class);
    }

    @Override
    @Transactional
    public void saveOrder(String secKillId, SeckillOrderInfo orderInfo) {
        Seckill seckill = validAvailable(orderInfo.getItemId());  // 校验秒杀是否有效
        TbItem item = checkStock(seckill.getItemId());  // 校验库存

        // 预扣库存
        if (itemMapper.updateNumByOptimistic(item) != 1) { // 乐观锁更新库存，防止高并发超卖的现象发生
            throw new SeckillConcurrentFailException("抢购失败");
        }

        // 生成订单对象
        Long orderId = IDUtil.getRandomId();
        TbOrder order = new TbOrder(orderId, orderInfo);

        if (orderMapper.insert(order) != 1) {  // 保存订单记录
            throw new HttpErrorException("生成订单失败");
        }
        // 保存订单单项商品记录
        TbOrderItem orderItem = new TbOrderItem(orderId, item, seckill);
        if (orderItemMapper.insert(orderItem) != 1) { // 保存订单单项记录
            throw new HttpErrorException("生成订单失败");
        }
        // 保存订单的物流信息
        TbOrderShipping orderShipping = new TbOrderShipping(orderId, orderInfo);
        if (orderShippingMapper.insert(orderShipping) != 1) {
            throw new HttpErrorException("生成物流信息失败");
        }

        // 商品库存发生了变化，直接删除缓存，等待下次读请求去数据库中拿到新的数据再写入缓存
        redis.hdel(CACHE_GOOD, item.toString());
        redis.del(CACHE_SECKILL_ALL);
    }

    @Override
    public void publishSeckill(Seckill seckill) {
        if (seckill.getStartTime().getTime() < System.currentTimeMillis()) {
            throw new HttpErrorException("开始必须大于当前时间");
        }
        if (seckill.getStartTime().getTime() > seckill.getEndTime().getTime()) {
            throw new HttpErrorException("结束时间必须大于开始时间");
        }

        TbItem item = itemMapper.selectByPrimaryKey(seckill.getItemId());
        if (item == null) {
            throw new HttpErrorException("该秒杀商品不存在");
        }

        String seckillId = IDUtil.getUUID();
        seckill.setId(seckillId);

        int expiredTime = (int) ((seckill.getEndTime().getTime() - seckill.getStartTime().getTime()) / 1000);  // 秒杀过期时间
        redis.setex(getSeckillKey(item.getId()), expiredTime, JSON.toJSONString(seckill));

        // 删除所有秒杀商品的缓存
        redis.del(CACHE_SECKILL_ALL);
    }

    private Seckill validAvailable(Long itemId) {
        String json = redis.get(getSeckillKey(itemId));
        if (json == null) {
            throw new HttpErrorException("该秒杀无效或已过期");
        }

        Seckill seckill = JSON.parseObject(json, Seckill.class);
        long startTime = seckill.getStartTime().getTime();
        long endTime = seckill.getEndTime().getTime();
        long now = System.currentTimeMillis();

        if (now < startTime) {
            throw new HttpErrorException("秒杀还未开始");
        }
        if (now > endTime) {
            throw new HttpErrorException("秒杀已经结束");
        }
        return seckill;
    }

    /**
     * 校验商品是否还有库存， 有则返回 TbItem 对象
     */
    private TbItem checkStock(Long itemId) {
        // 从缓存中读取商品的库存数据
        String json = redis.hget(CACHE_GOOD, itemId.toString());
        TbItem item = JSON.parseObject(json, TbItem.class);

        if (item.getNum() == 0) {
            throw new HttpErrorException("库存不足");
        }
        return item;
    }

    private static String getSeckillKey(Long itemId) {
        return SECKILL_PRE + ":" + itemId.toString();
    }
}
