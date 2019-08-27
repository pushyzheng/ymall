package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.pushy.ymall.common.exception.HttpErrorException;
import site.pushy.ymall.common.limit.RateLimit;
import site.pushy.ymall.common.util.MD5Util;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.*;
import site.pushy.ymall.service.SeckillService;
import site.pushy.ymall.web.sender.SeckillSender;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "秒杀相关接口")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private SeckillSender seckillSender;

    @GetMapping("/seckill")
    @RateLimit(time = 5, count = 1)  // 5秒内只能请求一次
    @ApiOperation("查询所有秒杀的活动")
    public String listSeckill() {
        List<SeckillDTO> result = seckillService.listSeckill();
        return RespUtil.success(result);
    }

    @GetMapping("/seckill/task")
    @ApiOperation("异步查询秒杀进度")
    public String querySeckillTask(@RequestParam Long userId, @RequestParam Long itemId) {
        return RespUtil.success(seckillService.getSeckillTask(userId, itemId));
    }

    @PostMapping("/seckill")
    @RateLimit(time = 5, count = 1)  // 5秒内只能请求一次
    @ApiOperation("异步秒杀请求")
    public String seckill(@RequestParam String seckillId,
                          @Valid @RequestBody SeckillOrderInfo orderInfo) {
        seckillSender.saveOrder(seckillId, orderInfo);
        return RespUtil.success();
    }

}
