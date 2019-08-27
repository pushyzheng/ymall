package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.Product;
import site.pushy.ymall.service.RecordService;

import java.util.List;

@RestController
@Api(tags = "记录相关接口")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/member/records")
    @ApiOperation("用户的浏览记录")
    public String listItemRecord(@RequestParam Long userId) {
        List<Product> products = recordService.listItemRecord(userId);
        return RespUtil.success(products);
    }

}
