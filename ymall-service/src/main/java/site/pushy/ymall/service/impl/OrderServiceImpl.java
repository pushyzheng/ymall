package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.common.util.IDUtil;
import site.pushy.ymall.dao.TbItemMapper;
import site.pushy.ymall.dao.TbOrderItemMapper;
import site.pushy.ymall.dao.TbOrderMapper;
import site.pushy.ymall.dao.TbOrderShippingMapper;
import site.pushy.ymall.pojo.DO.*;
import site.pushy.ymall.pojo.dto.*;
import site.pushy.ymall.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String CART_PRE = "cart";

    @Autowired
    private JedisClient redis;

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public PageOrder listOrder(Long userId, int page, int size) {
        if (page <= 0) page = 1;

        PageHelper.startPage(page, size);

        PageOrder pageOrder = new PageOrder();
        List<Order> result = new ArrayList<>();

        // 条件查询出所有的订单
        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("create_time DESC");
        List<TbOrder> orderList = orderMapper.selectByExample(example);

        for (TbOrder tbOrder : orderList) {
            Order order = getOrderById(Long.valueOf(tbOrder.getOrderId()));
            result.add(order);
        }

        pageOrder.setTotal(getMemberOrderCount(userId));
        pageOrder.setData(result);
        return pageOrder;
    }

    @Override
    public Order getOrderById(Long orderId) {
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(String.valueOf(orderId));
        if (tbOrder == null) {
            throw new RuntimeException("通过id获取订单失败");
        }

        Order order = new Order(tbOrder);
        String validTime = judgeOrder(tbOrder); // 校验订单是否失效
        if (validTime != null) {
            order.setFinishDate(validTime);
        }

        // 获取订单的物流地址
        TbOrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(tbOrder.getOrderId());
        TbAddress address = new TbAddress(orderShipping);
        order.setAddressInfo(address);

        // 获取订单的所有商品列表
        TbOrderItemExample exampleItem = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteriaItem = exampleItem.createCriteria();
        criteriaItem.andOrderIdEqualTo(tbOrder.getOrderId());
        List<TbOrderItem> listItem = orderItemMapper.selectByExample(exampleItem);
        List<CartProduct> listProduct = new ArrayList<>();
        for (TbOrderItem tbOrderItem : listItem) {
            CartProduct cartProduct = new CartProduct(tbOrderItem);
            listProduct.add(cartProduct);
        }
        order.setGoodsList(listProduct);
        return order;
    }

    @Override
    public int cancelOrder(Long orderId) {
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(String.valueOf(orderId));
        if (tbOrder == null) {
            throw new RuntimeException("通过id获取订单失败");
        }
        tbOrder.setStatus(5);
        tbOrder.setCloseTime(new Date());
        if (orderMapper.updateByPrimaryKey(tbOrder) != 1) {
            throw new RuntimeException("取消订单失败");
        }
        return 1;
    }

    @Override
    public Long saveOrder(OrderInfo orderInfo) {
        // 生成订单对象
        Long orderId = IDUtil.getRandomId();
        TbOrder order = new TbOrder(orderId, orderInfo);

        if (orderMapper.insert(order) != 1) {  // 保存订单记录
            throw new RuntimeException("生成订单失败");
        }

        List<CartProduct> list = orderInfo.getGoodsList();
        for (CartProduct product : list) {
            TbOrderItem orderItem = new TbOrderItem(orderId, product);
            if (orderItemMapper.insert(orderItem) != 1) { // 保存订单单项记录
                throw new RuntimeException("生成订单失败");
            }
        }
        // 保存订单的物流信息
        TbOrderShipping orderShipping = new TbOrderShipping(orderId, orderInfo);
        if (orderShippingMapper.insert(orderShipping) != 1) {
            throw new RuntimeException("生成物流信息失败");
        }
        // 预扣库存
        for (CartProduct cartProduct : orderInfo.getGoodsList()) {
            Long itemId = cartProduct.getProductId();
            TbItem product = itemMapper.selectByPrimaryKey(itemId);
            if (itemMapper.updateNumByOptimistic(product) != 1) { // 乐观锁更新库存，防止高并发超卖的现象发生
                throw new RuntimeException("更新库存失败");
            }
        }
        // 从购物车中删除订单中的所有商品
        removeCardProducts(orderInfo);
        return orderId;
    }

    @Override
    public int removeOrder(Long orderId) {
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(String.valueOf(orderId));
        List<TbOrderItem> list = orderItemMapper.selectByExample(example);
        for (TbOrderItem tbOrderItem : list) {  // 删除订单项目
            if (orderItemMapper.deleteByPrimaryKey(tbOrderItem.getId()) != 1) {
                throw new RuntimeException("删除订单商品失败");
            }
        }
        // 删除订单物流信息
        if (orderShippingMapper.deleteByPrimaryKey(String.valueOf(orderId)) != 1) {
            throw new RuntimeException("删除物流失败");
        }
        // 最后再来删除订单记录
        if (orderMapper.deleteByPrimaryKey(String.valueOf(orderId)) != 1) {
            throw new RuntimeException("删除订单失败");
        }
        return 1;
    }

    @Override
    public void payOrder(Payment payment) {
        TbOrder order = orderMapper.selectByPrimaryKey(payment.getOrderId().toString());
        order.setStatus(1);

        // 更新订单信息
        if (orderMapper.updateByPrimaryKey(order) != 1) {
            throw new RuntimeException("支付失败");
        }
    }

    /**
     * 删除购物车中含该订单的商品
     */
    private void removeCardProducts(OrderInfo orderInfo) {
        for (CartProduct product : orderInfo.getGoodsList()) {
            try {
                List<String> cardProducts = redis.hvals(CART_PRE + ":" + orderInfo.getUserId());
                for (String json : cardProducts) {
                    CartProduct cart = JSON.parseObject(json, CartProduct.class);
                    if (cart.getProductId().equals(product.getProductId())) {
                        redis.hdel(CART_PRE + ":" + orderInfo.getUserId(), cart.getProductId() + "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断订单是否超时未支付导致失效
     */
    private String judgeOrder(TbOrder tbOrder) {
        String result = null;
        if (tbOrder.getStatus() == 0) {
            // 判断是否已超1天
            long diff = System.currentTimeMillis() - tbOrder.getCreateTime().getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if (days >= 1) {
                // 设置失效
                tbOrder.setStatus(5);
                tbOrder.setCloseTime(new Date());
                if (orderMapper.updateByPrimaryKey(tbOrder) != 1) {
                    throw new RuntimeException("更新订单失效失败");
                }
            } else {
                //返回到期时间
                long time = tbOrder.getCreateTime().getTime() + 1000 * 60 * 60 * 24;
                result = String.valueOf(time);
            }
        }
        return result;
    }

    /**
     * 获取用户的订单总数量
     */
    private int getMemberOrderCount(Long userId) {
        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TbOrder> listOrder = orderMapper.selectByExample(example);
        if (listOrder != null) {
            return listOrder.size();
        }
        return 0;
    }
}
