package site.pushy.ymall.pojo.dto;

import site.pushy.ymall.pojo.DO.TbAddress;
import site.pushy.ymall.pojo.DO.TbOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class Order implements Serializable {

    private Long orderId;

    private BigDecimal orderTotal;

    private TbAddress addressInfo;

    private List<CartProduct> goodsList;

    private String orderStatus;

    private String createDate;

    private String closeDate;

    private String finishDate;

    private String payDate;

    public Order() {
    }

    public Order(TbOrder tbOrder) {
        this.setOrderId(Long.valueOf(tbOrder.getOrderId()));
        this.setOrderStatus(String.valueOf(tbOrder.getStatus()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createDate = formatter.format(tbOrder.getCreateTime());
        this.setCreateDate(createDate);
        //payDate
        if (tbOrder.getPaymentTime() != null) {
            String payDate = formatter.format(tbOrder.getPaymentTime());
            this.setPayDate(payDate);
        }
        //closeDate
        if (tbOrder.getCloseTime() != null) {
            String closeDate = formatter.format(tbOrder.getCloseTime());
            this.setCloseDate(closeDate);
        }
        //finishDate
        if (tbOrder.getEndTime() != null && tbOrder.getStatus() == 4) {
            String finishDate = formatter.format(tbOrder.getEndTime());
            this.setFinishDate(finishDate);
        }

        // 订单总金额
        if (tbOrder.getPayment() == null) {
            this.setOrderTotal(new BigDecimal(0));
        } else {
            this.setOrderTotal(tbOrder.getPayment());
        }
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public TbAddress getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(TbAddress addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<CartProduct> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CartProduct> goodsList) {
        this.goodsList = goodsList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
