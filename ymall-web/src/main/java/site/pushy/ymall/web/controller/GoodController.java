package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.pushy.ymall.common.limit.RateLimit;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.*;
import site.pushy.ymall.service.GoodService;
import site.pushy.ymall.service.RecordService;
import site.pushy.ymall.service.SeckillService;

@RestController
@Api(tags = "商品相关接口")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/goods/home")
    @ApiOperation("主页版块商品列表")
    public String home() {
        return RespUtil.success(goodService.getHome());
    }

    @GetMapping("/goods/productDet")
    @ApiOperation("商品详情")
    public String productDet(@RequestParam Long productId, @RequestParam(required = false) Long userId) {
        ProductDet productDet = goodService.getGoodById(productId);
        if (userId != null && userId != 0L) {
            recordService.saveItemRecord(productId, userId);
        }
        // 判断该商品是否参与秒杀
        Seckill seckill = seckillService.isSeckillGood(productId);
        if (seckill != null) {
            productDet.setSeckillGood(true);
            productDet.setSeckill(seckill);
            boolean started = System.currentTimeMillis() > seckill.getStartTime().getTime();
            productDet.setSeckillStarted(started);
        }
        return RespUtil.success(productDet);
    }

    @GetMapping("/goods/allGoods")
    @ApiOperation("所有商品")
    public String getAllGoods(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(defaultValue = "") String sort,
                              @RequestParam(defaultValue = "") Long cid,
                              @RequestParam(defaultValue = "-1") int priceGt,
                              @RequestParam(defaultValue = "-1") int priceLte) {
        AllGoodsResult result = goodService.getAllGoods(page, size, sort, cid, priceGt, priceLte);
        return RespUtil.success(result);
    }

}
