package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.dao.TbItemMapper;
import site.pushy.ymall.pojo.DO.TbItem;
import site.pushy.ymall.pojo.dto.Product;
import site.pushy.ymall.service.RecordService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private JedisClient redis;

    @Autowired
    private TbItemMapper itemMapper;

    private static final String RECORD_PRE = "record";

    @Override
    public List<Product> listItemRecord(Long userId) {
        List<String> records = redis.lrange(RECORD_PRE + ":" + userId, 0, 20);
        List<Product> products = new ArrayList<>();

        for (String json : records) {
            products.add(JSON.parseObject(json, Product.class));
        }
        return products;
    }

    @Override
    public void saveItemRecord(Long productId, Long userId) {
        String key = RECORD_PRE + ":" + userId;
        List<String> records = redis.lgetAll(key);

        boolean exists = false;
        for (String json : records) {
            Product product = JSON.parseObject(json, Product.class);
            if (product.getProductId().equals(productId)) {
                exists = true;
            }
        }

        if (!exists) {
            TbItem item = itemMapper.selectByPrimaryKey(productId);
            Product product = new Product(item);
            redis.lpush(key, JSON.toJSONString(product));
        }
    }

}
