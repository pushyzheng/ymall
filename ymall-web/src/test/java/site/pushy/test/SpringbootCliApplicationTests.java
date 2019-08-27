package site.pushy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.pushy.ymall.YmallWebApplication;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.jedis.JedisClientPool;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YmallWebApplication.class)
public class SpringbootCliApplicationTests {

    @Test
    public void contextLoads() {
        JedisClient redis = new JedisClientPool();
        List<String> a = redis.lgetAll("3123123");
        System.out.println(a);
    }

}
