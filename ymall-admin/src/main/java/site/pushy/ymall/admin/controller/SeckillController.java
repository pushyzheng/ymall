package site.pushy.ymall.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.Seckill;
import site.pushy.ymall.service.SeckillService;

import javax.validation.Valid;

/**
 * @author pushyzheng
 * @since 2019/8/27
 */
@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @PostMapping("/seckill/publish")
    public String publishSeckill(@Valid @RequestBody Seckill seckill) {
        seckillService.publishSeckill(seckill);
        return RespUtil.success(seckill);
    }
}
