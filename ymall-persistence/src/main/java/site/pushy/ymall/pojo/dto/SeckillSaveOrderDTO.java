package site.pushy.ymall.pojo.dto;


public class SeckillSaveOrderDTO {

    private String seckillId;

    private SeckillOrderInfo orderInfo;

    public SeckillSaveOrderDTO() {
    }

    public SeckillSaveOrderDTO(String seckillId, SeckillOrderInfo orderInfo) {
        this.seckillId = seckillId;
        this.orderInfo = orderInfo;
    }

    public String getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(String seckillId) {
        this.seckillId = seckillId;
    }

    public SeckillOrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(SeckillOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }


}
