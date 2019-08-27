package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.dao.TbItemMapper;
import site.pushy.ymall.pojo.DO.TbItem;
import site.pushy.ymall.pojo.dto.Cart;
import site.pushy.ymall.pojo.dto.CartProduct;
import site.pushy.ymall.service.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private JedisClient redis;

    private static final String CART_PRE = "cart";

    @Override
    public List<CartProduct> listCart(Long userId) {
        List<String> jsonList = redis.hvals(CART_PRE + ":" + userId);
        List<CartProduct> resp = new ArrayList<>();

        for (String json : jsonList) {
            CartProduct cartProduct = JSON.parseObject(json, CartProduct.class);
            resp.add(cartProduct);
        }
        return resp;
    }

    @Override
    public boolean addCart(Cart cart) {
        Long userId = cart.getUserId();
        Long itemId = cart.getProductId();
        int num = cart.getProductNum();

        Boolean hexists = redis.hexists(CART_PRE + ":" + userId, itemId + "");
        boolean result = true;

        if (hexists) { //如果存在数量相加
            String json = redis.hget(CART_PRE + ":" + userId, itemId + "");
            if (json != null) {
                CartProduct cartProduct = JSON.parseObject(json, CartProduct.class);
                cartProduct.setProductNum(cartProduct.getProductNum() + num);
                redis.hset(CART_PRE + ":" + userId, itemId + "", JSON.toJSONString(cartProduct));
            } else {
                result = false;
            }
            return result;
        }

        // 如果没有则根据id取出商品的信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        if (item == null) {
            return false;
        }
        CartProduct cartProduct = new CartProduct(item);
        cartProduct.setProductNum((long) num);
        cartProduct.setChecked("1");
        redis.hset(CART_PRE + ":" + userId, itemId + "", JSON.toJSONString(cartProduct));
        return true;
    }

    @Override
    public int updateCart(Cart cart) {
        Long userId = cart.getUserId();
        Long itemId = cart.getProductId();

        String json = redis.hget(CART_PRE + ":" + userId, itemId + "");
        if (json == null) {
            return 0;
        }
        CartProduct cartProduct = JSON.parseObject(json, CartProduct.class);
        cartProduct.setProductNum((long) cart.getProductNum());
        cartProduct.setChecked(cart.getChecked());
        redis.hset(CART_PRE + ":" + userId, itemId + "", JSON.toJSONString(cartProduct));
        return 1;
    }

    @Override
    public int checkAll(Cart cart) {
        List<String> jsonList = redis.hvals(CART_PRE + ":" + cart.getUserId());

        for (String json : jsonList) {
            CartProduct cartProduct = JSON.parseObject(json, CartProduct.class);
            if ("true".equals(cart.getChecked())) {
                cartProduct.setChecked("1");
            } else if ("false".equals(cart.getChecked())) {
                cartProduct.setChecked("0");
            } else {
                return 0;
            }
            redis.hset(CART_PRE + ":" + cart.getUserId(), cartProduct.getProductId() + "",
                    JSON.toJSONString(cartProduct));
        }
        return 1;
    }

    @Override
    public int removeCartItem(Cart cart) {
        redis.hdel(CART_PRE + ":" + cart.getUserId(), cart.getProductId() + "");
        return 1;
    }

    @Override
    public void delChecked(Long userId) {
        List<String> jsonList = redis.hvals(CART_PRE + ":" + userId);
        for (String json : jsonList) {
            CartProduct cartProduct = JSON.parseObject(json, CartProduct.class);
            if ("1".equals(cartProduct.getChecked())) {
                redis.hdel(CART_PRE + ":" + userId, cartProduct.getProductId() + "");
            }
        }
    }
}
