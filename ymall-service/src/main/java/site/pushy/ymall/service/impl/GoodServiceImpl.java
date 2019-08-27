package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.dao.TbItemDescMapper;
import site.pushy.ymall.dao.TbItemMapper;
import site.pushy.ymall.dao.TbPanelContentMapper;
import site.pushy.ymall.dao.TbPanelMapper;
import site.pushy.ymall.service.GoodService;
import site.pushy.ymall.pojo.DO.*;
import site.pushy.ymall.pojo.dto.*;

import static site.pushy.ymall.common.config.RedisPrefix.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private TbPanelMapper panelMapper;

    @Autowired
    private TbPanelContentMapper panelContentMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private JedisClient redis;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TbPanel> getHome() {
        // 查询缓存
        String json = redis.get(CACHE_HOME);
        if (json != null) {
            return JSON.parseArray(json, TbPanel.class);
        }

        // 条件查询出所有的版块
        TbPanelExample example = new TbPanelExample();
        TbPanelExample.Criteria criteria = example.createCriteria();
        criteria.andPositionEqualTo(0);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("sort_order");
        List<TbPanel> panels = panelMapper.selectByExample(example);

        // 遍历版块，条件查询各个版块的内容
        for (TbPanel panel : panels) {
            TbPanelContentExample exampleContent = new TbPanelContentExample();
            exampleContent.setOrderByClause("sort_order");
            TbPanelContentExample.Criteria criteriaContent = exampleContent.createCriteria();
            criteriaContent.andPanelIdEqualTo(panel.getId());
            List<TbPanelContent> contentList = panelContentMapper.selectByExample(exampleContent);

            for (TbPanelContent content : contentList) {
                if (content.getProductId() != null) {
                    TbItem tbItem = itemMapper.selectByPrimaryKey(content.getProductId());
                    content.setProductName(tbItem.getTitle());
                    content.setSalePrice(tbItem.getPrice());
                    content.setSubTitle(tbItem.getSellPoint());
                    content.setProductImageBig(tbItem.getImages()[0]);
                }
            }
            panel.setPanelContents(contentList);
        }
        // 加入缓存
        redis.set(CACHE_HOME, JSON.toJSONString(panels));
        return panels;
    }

    @Override
    public ProductDet getGoodById(Long productId) {
        // 先从缓存中查找
        String json = redis.hget(CACHE_GOOD, productId.toString());
        if (json != null) {
            return JSON.parseObject(json, ProductDet.class);
        }

        TbItem tbItem = itemMapper.selectByPrimaryKey(productId);
        if (tbItem == null) {
            // 如果第一次查询出来商品是空，那么将该ID将如到缓存. 这是为什么呢？
            // 为了防止黑客的缓存穿透攻击，避免每次都落地到数据库
            redis.hset(CACHE_GOOD, productId.toString(), "null");
            throw new HttpErrorException("商品不存在");
        }
        ProductDet productDet = new ProductDet(tbItem);
        // 商品详细描述
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(productId);
        productDet.setDetail(tbItemDesc.getItemDesc());
        // 详细描述图片
        if (tbItem.getImage() != null && !tbItem.getImage().isEmpty()) {
            String[] images = tbItem.getImage().split(",");
            productDet.setProductImageBig(images[0]);
            List list = new ArrayList();
            for (int i = 0; i < images.length; i++) {
                list.add(images[i]);
            }
            productDet.setProductImageSmall(list);
        }
        // 加入缓存
        redis.hset(CACHE_GOOD, productId.toString(), JSON.toJSONString(productDet));
        return productDet;
    }

    @Override
    public AllGoodsResult getAllGoods(int page, int size, String sort, Long cid, int priceGt, int priceLte) {
        AllGoodsResult result = new AllGoodsResult();
        List<Product> list = new ArrayList<>();

        if (page <= 0) page = 1;
        PageHelper.startPage(page, size);

        //判断条件
        String orderCol = "created";
        String orderDir = "desc";
        if (sort.equals("1")) {
            orderCol = "price";
            orderDir = "asc";
        } else if (sort.equals("-1")) {
            orderCol = "price";
            orderDir = "desc";
        } else {
            orderCol = "created";
            orderDir = "desc";
        }

        List<TbItem> tbItemList = itemMapper.selectItemFront(cid, orderCol, orderDir, priceGt, priceLte);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        for (TbItem tbItem : tbItemList) {
            Product product = new Product(tbItem);
            list.add(product);
        }

        result.setData(list);
        result.setTotal((int) pageInfo.getTotal());

        return result;
    }
}
