package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.Order;
import site.pushy.ymall.pojo.dto.OrderInfo;
import site.pushy.ymall.pojo.dto.PageOrder;
import site.pushy.ymall.pojo.dto.Payment;
import site.pushy.ymall.service.OrderService;

@RestController
@Api(tags = "订单相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/member/orderList")
    @ApiOperation("订单列表")
    public String getOrderList(String userId,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "5") int size) {

        PageOrder pageOrder = orderService.listOrder(Long.valueOf(userId), page, size);
        return RespUtil.success(pageOrder);
    }

    @GetMapping(value = "/member/orderDetail")
    @ApiOperation("订单详情")
    public String getOrder(String orderId) {

        Order order = orderService.getOrderById(Long.valueOf(orderId));
        return RespUtil.success(order);
    }

    @PostMapping(value = "/member/addOrder")
    @ApiOperation("提交订单")
    @Transactional
    public String addOrder(@RequestBody OrderInfo orderInfo) {
        Long orderId = orderService.saveOrder(orderInfo);
        return RespUtil.success(orderId.toString());
    }

    @PostMapping(value = "/member/cancelOrder")
    @ApiOperation("取消订单")
    public String cancelOrder(@RequestBody Order order) {

        int result = orderService.cancelOrder(order.getOrderId());
        return RespUtil.success(result);
    }

    @GetMapping(value = "/member/delOrder")
    @ApiOperation("删除订单")
    public String delOrder(String orderId) {
        int result = orderService.removeOrder(Long.valueOf(orderId));
        return RespUtil.success(result);
    }

    @PostMapping("/member/payOrder")
    @ApiOperation("支付订单")
    public String payOrder(@RequestBody Payment payment) {
        orderService.payOrder(payment);
        return RespUtil.success(true);
    }


}
