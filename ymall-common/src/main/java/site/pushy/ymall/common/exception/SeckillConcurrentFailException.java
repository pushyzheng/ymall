package site.pushy.ymall.common.exception;

/**
 * 秒杀并发失败异常
 *
 * @author pushy zheng
 * @since 2019/8/8
 */
public class SeckillConcurrentFailException extends HttpErrorException {

    public SeckillConcurrentFailException(String message) {
        super(message);
    }
}
