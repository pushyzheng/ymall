package site.pushy.ymall.common.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Exrickx
 */
public interface JedisClient {

	Set<String> keys(String pattern);

	String set(String key, String value);

	Long setnx(String key, String value);

	String setex(String key, int seconds, String value);

	String get(String key);

	Boolean exists(String key);

	Long expire(String key, int seconds);

	Long ttl(String key);

	Long incr(String key);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Map<String, String> hgetAll(String key);

	Long hdel(String key, String... field);

	Boolean hexists(String key, String field);

	List<String> hvals(String key);

	Long del(String key);

	Long lpush(String key, String... strs);

	Long rpush(String key, String... strs);

	List<String> lrange(String key, long start, long end);

	List<String> lgetAll(String key);

	Long llen(String key);

	Object eval(String script);

	Object eval(String script, List<String> keys, List<String> args);

}
