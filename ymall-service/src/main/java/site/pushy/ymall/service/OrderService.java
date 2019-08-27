package site.pushy.ymall.service;

import site.pushy.ymall.pojo.dto.Order;
import site.pushy.ymall.pojo.dto.OrderInfo;
import site.pushy.ymall.pojo.dto.PageOrder;
import site.pushy.ymall.pojo.dto.Payment;

public interface OrderService {

    /**
     * 分页获得用户订单
     * @param userId
     * @param page
     * @param size
     */
    PageOrder listOrder(Long userId, int page, int size);

    /**
     * 获得单个订单
     * @param orderId
     */
    Order getOrderById(Long orderId);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    int cancelOrder(Long orderId);

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    Long saveOrder(OrderInfo orderInfo);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int removeOrder(Long orderId);

    void payOrder(Payment payment);

}
