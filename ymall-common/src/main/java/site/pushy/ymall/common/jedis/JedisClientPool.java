package site.pushy.ymall.common.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Exrickx
 */
public class JedisClientPool implements JedisClient {

    private JedisPool jedisPool;

    private static int TIMEOUT = 10 * 1000;
    private static int MAX_WAIT = 15 * 1000;

    public JedisClientPool() {
        initialPool();
    }

    private void initialPool() {
        String host = "localhost";
        int port = 6379;
        String password = "";

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数，如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(400);
            //最大空闲数，控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
            config.setMaxIdle(50);
            //最小空闲数
            config.setMinIdle(10);
            //是否在从池中取出连接前进行检验，如果检验失败，则从池中去除连接并尝试取出另一个
            config.setTestOnBorrow(true);
            //在return给pool时，是否提前进行validate操作
            config.setTestOnReturn(true);
            //在空闲时检查有效性，默认false
            config.setTestWhileIdle(true);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
            //这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(30000);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(60000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(1000);
            //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(MAX_WAIT);

            if (password != null && !password.isEmpty()) {
                jedisPool = new JedisPool(config, host, port, TIMEOUT, password);
            } else {
                jedisPool = new JedisPool(config, host, port, TIMEOUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Set<String> keys(String pattern) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.keys(pattern);
        jedis.close();
        return result;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }



    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.setnx(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.setex(key, seconds, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> result = jedis.hgetAll(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.hexists(key, field);
        jedis.close();
        return result;
    }

    @Override
    public List<String> hvals(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = jedis.hvals(key);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public Long lpush(String key, String... strs) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.lpush(key, strs);
        jedis.close();
        return result;
    }

    @Override
    public Long rpush(String key, String... strs) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.rpush(key, strs);
        jedis.close();
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = jedis.lrange(key, start, end);
        jedis.close();
        return result;
    }

    @Override
    public List<String> lgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = jedis.lrange(key, 0, -1);
        jedis.close();
        return result;
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.llen(key);
        jedis.close();
        return result;
    }

    @Override
    public Object eval(String script) {
        Jedis jedis = jedisPool.getResource();
        Object result = jedis.eval(script);
        jedis.close();
        return result;
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = jedisPool.getResource();
        Object result = jedis.eval(script, keys, args);
        jedis.close();
        return result;
    }

}
