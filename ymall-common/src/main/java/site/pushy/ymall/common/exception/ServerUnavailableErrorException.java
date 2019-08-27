package site.pushy.ymall.common.exception;

/**
 * @author pushy zheng
 * @since 2019/8/6
 */
public class ServerUnavailableErrorException extends RuntimeException {

    public ServerUnavailableErrorException(String message) {
        super(message);
    }
}
