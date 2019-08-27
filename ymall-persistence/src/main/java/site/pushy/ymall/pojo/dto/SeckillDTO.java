package site.pushy.ymall.pojo.dto;

import site.pushy.ymall.pojo.DO.TbItem;

import java.math.BigDecimal;
import java.util.Date;

public class SeckillDTO {

    private String id;

    private Long itemId;

    private Date startTime;

    private Date endTime;

    private BigDecimal seckillPrice;

    private TbItem item;

    public SeckillDTO() {
    }

    public SeckillDTO(Seckill seckill, TbItem item) {
        this.id = seckill.getId();
        this.itemId = seckill.getItemId();
        this.startTime = seckill.getStartTime();
        this.endTime = seckill.getEndTime();
        this.seckillPrice = seckill.getSeckillPrice();
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "SeckillDTO{" +
                "id='" + id + '\'' +
                ", itemId=" + itemId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", seckillPrice=" + seckillPrice +
                ", item=" + item +
                '}';
    }
}
