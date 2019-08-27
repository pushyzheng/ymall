package site.pushy.ymall.web;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.pojo.DO.TbPanel;
import site.pushy.ymall.pojo.dto.Seckill;
import site.pushy.ymall.service.GoodService;

import java.util.List;
import java.util.Set;

import static site.pushy.ymall.common.config.RedisPrefix.*;

/**
 * 该类实现了 ApplicationRunner 接口，会在 SpringBoot 初始化后运行 run 方法
 * 主要用于缓存预热，防止在服务启动之后大量的请求一瞬间直接打到了服务器上
 *
 * @author pushyzheng
 * @since 2019/8/27
 */
@Component
public class CacheWarmingUpStarter implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodService goodService;

    @Autowired
    private JedisClient redis;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("开始进行缓存预热...");

        // 预热首页数据缓存
        goodService.getHome();

        // 预热秒杀商品详情页缓存
        // 通过 keys 命令的正则去查询到所有的秒杀商品的键
        Set<String> keys = redis.keys(SECKILL_PRE + ":*");
        // 去缓存里获取商品的信息
        for (String key : keys) {
            Seckill seckill = JSON.parseObject(redis.get(key), Seckill.class);
            goodService.getGoodById(seckill.getItemId());  // 方法内会自动加入到缓存中
        }
    }
}
