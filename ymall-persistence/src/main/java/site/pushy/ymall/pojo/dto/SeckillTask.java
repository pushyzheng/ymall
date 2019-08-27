package site.pushy.ymall.pojo.dto;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
public class SeckillTask {

    private boolean finished;

    private boolean succeed;

    private String errMessage;

    public SeckillTask() {
        this.finished = false;
        this.succeed = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
