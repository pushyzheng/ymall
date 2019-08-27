package site.pushy.ymall.pojo.DO;

import site.pushy.ymall.common.util.IDUtil;
import site.pushy.ymall.pojo.dto.CartProduct;
import site.pushy.ymall.pojo.dto.Seckill;

import java.math.BigDecimal;

public class TbOrderItem {

    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private BigDecimal price;

    private BigDecimal totalFee;

    private String picPath;

    public TbOrderItem() {
    }

    /**
     * 根据购物车对象CartProduct，来创建订单项目对象
     * @param product
     */
    public TbOrderItem(Long orderId, CartProduct product) {
        // 生成订单商品的ID
        this.id = String.valueOf(IDUtil.getRandomId());
        this.setItemId(String.valueOf(product.getProductId()));
        this.setOrderId(String.valueOf(orderId));
        this.setNum(Math.toIntExact(product.getProductNum()));
        this.setPrice(product.getSalePrice());
        this.setTitle(product.getProductName());
        this.setPicPath(product.getProductImg());
        this.setTotalFee(product.getSalePrice().multiply(BigDecimal.valueOf(product.getProductNum())));
    }

    public TbOrderItem(Long orderId, TbItem item, Seckill seckill) {
        this.id = String.valueOf(IDUtil.getRandomId());
        this.setItemId(String.valueOf(item.getId()));
        this.setOrderId(String.valueOf(orderId));
        this.setNum(1);  // 秒杀的商品只有一件
        this.setPrice(seckill.getSeckillPrice());  // 设置为秒杀的价格
        this.setTitle(item.getTitle());
        this.setPicPath(item.getFirstImage());
        this.setTotalFee(seckill.getSeckillPrice());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }
}