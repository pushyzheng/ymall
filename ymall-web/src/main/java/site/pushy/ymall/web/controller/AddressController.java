package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.DO.TbAddress;
import site.pushy.ymall.service.AddressService;

import java.util.List;

@RestController
@Api(tags = "用户收货地址相关接口")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/member/addressList")
    @ApiOperation("查询用户所有收货地址")
    public String addressList(@RequestBody TbAddress tbAddress) {
        List<TbAddress> list = addressService.listAddress(tbAddress.getUserId());
        return RespUtil.success(list);
    }

    @PostMapping(value = "/member/address")
    @ApiOperation("查询某个收货地址")
    public String address(@RequestBody TbAddress tbAddress) {
        TbAddress address = addressService.getAddressById(tbAddress.getAddressId());
        return RespUtil.success(address);
    }

    @PostMapping(value = "/member/addAddress")
    @ApiOperation("添加收货地址")
    public String addAddress(@RequestBody TbAddress tbAddress) {

        int result = addressService.saveAddress(tbAddress);
        return RespUtil.success(result);
    }

    @PostMapping(value = "/member/updateAddress")
    @ApiOperation("更新收货地址")
    public String updateAddress(@RequestBody TbAddress tbAddress) {

        int result = addressService.updateAddress(tbAddress);
        return RespUtil.success(result);
    }

    @PostMapping(value = "/member/delAddress")
    @ApiOperation("删除收货地址")
    public String delAddress(@RequestBody TbAddress tbAddress) {

        int result = addressService.removeAddress(tbAddress);
        return RespUtil.success(result);
    }

}
