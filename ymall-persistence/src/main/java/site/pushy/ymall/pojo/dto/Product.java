package site.pushy.ymall.pojo.dto;

import site.pushy.ymall.pojo.DO.TbItem;

import java.math.BigDecimal;

public class Product {

    private Long productId;

    private BigDecimal salePrice;

    private String productName;

    private String subTitle;

    private String productImageBig;

    public Product() {
    }

    public Product(TbItem tbItem) {
        this.setProductId(tbItem.getId());
        this.setProductName(tbItem.getTitle());
        this.setSalePrice(tbItem.getPrice());
        this.setSubTitle(tbItem.getSellPoint());
        this.setProductImageBig(tbItem.getImages()[0]);
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getProductImageBig() {
        return productImageBig;
    }

    public void setProductImageBig(String productImageBig) {
        this.productImageBig = productImageBig;
    }
}
