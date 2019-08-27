package site.pushy.ymall.common.config;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
public class RabbitConstants {

    public static final String QUEUE_SECKILL_SAVE_ORDER = "seckill-save-order";

    public static final String QUEUE_SECKILL_SAVE_ORDER_CALLBACK = "seckill-save-order-callback";

    public static final boolean DURABLE = true;

    public static final boolean EXCLUSIVE = false;

    public static final boolean AUTO_DELETE = true;
}
