package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.*;
import site.pushy.ymall.service.CartService;

@RestController
@Api(tags = "购物车相关接口")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/member/cartList")
    @ApiOperation("购物车列表")
    public String getCartList(@RequestBody Cart cart) {
        return RespUtil.success(cartService.listCart(cart.getUserId()));
    }

    @PostMapping("/member/addCart")
    @ApiOperation("添加购物车")
    public String addCart(@RequestBody Cart cart) {
        return RespUtil.success(cartService.addCart(cart));
    }

    @PostMapping("/member/cartEdit")
    @ApiOperation("编辑购物车")
    public String editCart(Cart cart) {
        int result = cartService.updateCart(cart);
        return RespUtil.success(result);
    }

    @PostMapping(value = "/member/editCheckAll")
    @ApiOperation("勾选购物车所有商品")
    public String editCheckAll(@RequestBody Cart cart) {
        int result = cartService.checkAll(cart);
        return RespUtil.success(result);
    }

    @PostMapping(value = "/member/cartDel")
    @ApiOperation("删除购物车商品")
    public String deleteCartItem(@RequestBody Cart cart) {
        int result = cartService.removeCartItem(cart);
        return RespUtil.success(result);
    }

    @PostMapping(value = "/member/delCartChecked")
    @ApiOperation("删除勾选的购物车商品")
    public String delChecked(@RequestBody Cart cart) {
        cartService.delChecked(cart.getUserId());
        return RespUtil.success();
    }

}
