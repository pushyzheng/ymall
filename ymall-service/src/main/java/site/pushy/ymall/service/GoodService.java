package site.pushy.ymall.service;


import site.pushy.ymall.pojo.DO.TbPanel;
import site.pushy.ymall.pojo.dto.AllGoodsResult;
import site.pushy.ymall.pojo.dto.ProductDet;

import java.util.List;

public interface GoodService {

    List<TbPanel> getHome();

    ProductDet getGoodById(Long productId);

    AllGoodsResult getAllGoods(int page, int size, String sort,
                               Long cid, int priceGt, int priceLte);

}
