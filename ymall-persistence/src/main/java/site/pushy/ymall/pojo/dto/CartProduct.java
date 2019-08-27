package site.pushy.ymall.pojo.dto;

import site.pushy.ymall.pojo.DO.TbItem;
import site.pushy.ymall.pojo.DO.TbOrderItem;

import java.math.BigDecimal;

public class CartProduct {

    private Long productId;

    private BigDecimal salePrice;

    private Long productNum;

    private Long limitNum;

    private String checked;

    private String productName;

    private String productImg;

    public CartProduct() {
    }

    public CartProduct(TbItem item) {
        this.productId = item.getId();
        this.productName = item.getTitle();
        this.salePrice = item.getPrice();
        this.productImg = item.getImages()[0];
        if (item.getLimitNum() == null) {
            this.limitNum = Long.valueOf(item.getLimitNum());
        } else if (item.getLimitNum() < 0 && item.getNum() < 0) {
            this.limitNum = (long) 10;
        } else {
            this.limitNum = Long.valueOf(item.getLimitNum());
        }
    }

    public CartProduct(TbOrderItem orderItem) {
        this.setProductId(Long.valueOf(orderItem.getItemId()));
        this.setProductName(orderItem.getTitle());
        this.setSalePrice(orderItem.getPrice());
        this.setProductNum(Long.valueOf(orderItem.getNum()));
        this.setProductImg(orderItem.getPicPath());
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public Long getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Long limitNum) {
        this.limitNum = limitNum;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
