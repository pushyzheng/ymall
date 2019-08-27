package site.pushy.ymall.common.config;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
public class RedisPrefix {

    public static final long TRUE = 1L;
    public static final long FALSE = 0L;

    public static final String SECKILL_ORDER_LOCK_KEY = "seckill-order-lock";

    public static final String SECKILL_PRE = "seckill";

    public static final String SECKILL_TASK = "seckill-task";

    public static final String CACHE_HOME = "cache:home";

    public static final String CACHE_GOOD = "cache:good";

    public static final String CACHE_SECKILL_ALL = "cache:seckill-all";

    public static final String QUEUE_IDEMPOTENT = "queue:idempotent";

    public static String getSeckillTaskKey(Long itemId) {
        return SECKILL_TASK + ":" + itemId;
    }
}
