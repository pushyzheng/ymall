package site.pushy.ymall.service;

import site.pushy.ymall.pojo.DO.TbAddress;

import java.util.List;

public interface AddressService {

    /**
     * 获取地址
     */
    List<TbAddress> listAddress(Long userId);

    /**
     * 获取单个
     */
    TbAddress getAddressById(Long addressId);

    /**
     * 添加
     */
    int saveAddress(TbAddress tbAddress);

    /**
     * 更新
     */
    int updateAddress(TbAddress tbAddress);

    /**
     * 删除
     */
    int removeAddress(TbAddress tbAddress);
}
