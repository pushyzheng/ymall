package site.pushy.ymall.pojo.dto;

/**
 * @author pushy zheng
 * @since 2019/8/8
 */
public class SeckillSaveOrderCallbackDTO {

    private boolean succeed;

    private String errMessage;

    private SeckillOrderInfo orderInfo;

    public boolean getSucceed() {
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

    public SeckillOrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(SeckillOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
