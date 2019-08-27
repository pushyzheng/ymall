package site.pushy.ymall.pojo.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class Seckill {

    private String id;

    @NotNull
    private Long itemId;

    @NotNull
    private Date startTime;

    @NotNull
    private Date endTime;

    @NotNull
    private BigDecimal seckillPrice;

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
}
