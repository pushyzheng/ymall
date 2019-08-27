package site.pushy.ymall.pojo.dto;

import site.pushy.ymall.pojo.DO.TbItem;

import java.math.BigDecimal;
import java.util.List;

public class ProductDet {

    private Long productId;

    private BigDecimal salePrice;

    private String productName;

    private String subTitle;

    private Long limitNum;

    private Integer num;

    private String productImageBig;

    private String detail;

    private List<?> productImageSmall;

    private boolean isSeckillGood;  // 当前是否参与秒杀

    private Seckill seckill;       // 秒杀对象

    private boolean seckillStarted;  // 秒杀是否已经开始

    public ProductDet() {
    }

    public ProductDet(TbItem item) {
        if (item == null) {
            throw new IllegalArgumentException("The item cannot be null");
        }

        this.setProductId(item.getId());        // 商品ID
        this.setProductName(item.getTitle());   // 商品名
        this.setSubTitle(item.getSellPoint());  // 卖点

        if (item.getLimitNum() != null && !item.getLimitNum().toString().isEmpty()) {
            this.setLimitNum(Long.valueOf(item.getLimitNum()));
        } else {
            this.setLimitNum(Long.valueOf(item.getNum()));
        }

        this.setNum(item.getNum());          // 库存
        if (item.getNum() < item.getLimitNum()) {  // 当库存数量小于限制购买数量时，将限制数量设置为库存数量
            this.setLimitNum((long) item.getNum());
        }
        this.setSalePrice(item.getPrice());  // 价格
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

    public Long getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Long limitNum) {
        this.limitNum = limitNum;
    }

    public String getProductImageBig() {
        return productImageBig;
    }

    public void setProductImageBig(String productImageBig) {
        this.productImageBig = productImageBig;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<?> getProductImageSmall() {
        return productImageSmall;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setProductImageSmall(List<?> productImageSmall) {
        this.productImageSmall = productImageSmall;
    }

    public boolean isSeckillGood() {
        return isSeckillGood;
    }

    public void setSeckillGood(boolean seckillGood) {
        isSeckillGood = seckillGood;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public boolean isSeckillStarted() {
        return seckillStarted;
    }

    public void setSeckillStarted(boolean seckillStarted) {
        this.seckillStarted = seckillStarted;
    }
}
