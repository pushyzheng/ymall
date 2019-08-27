package site.pushy.ymall.service;

import site.pushy.ymall.pojo.dto.Cart;
import site.pushy.ymall.pojo.dto.CartProduct;

import java.util.List;

public interface CartService {

    List<CartProduct> listCart(Long userId);

    boolean addCart(Cart cart);

    int updateCart(Cart cart);

    int checkAll(Cart cart);

    int removeCartItem(Cart cart);

    void delChecked(Long userId);
}
