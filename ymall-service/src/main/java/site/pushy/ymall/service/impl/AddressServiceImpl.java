package site.pushy.ymall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.dao.TbAddressMapper;
import site.pushy.ymall.pojo.DO.TbAddress;
import site.pushy.ymall.pojo.DO.TbAddressExample;
import site.pushy.ymall.service.AddressService;

import java.util.Collections;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TbAddressMapper addressMapper;

    @Override
    public List<TbAddress> listAddress(Long userId) {
        // 条件查询用户的所有地址
        TbAddressExample example = new TbAddressExample();
        TbAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TbAddress> list = addressMapper.selectByExample(example);
        if (list == null) {
            throw new RuntimeException("获取默认地址列表失败");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsDefault()) {
                Collections.swap(list, 0, i);
                break;
            }
        }
        return list;
    }

    @Override
    public TbAddress getAddressById(Long addressId) {
        TbAddress address = addressMapper.selectByPrimaryKey(addressId);
        if (address == null) {
            throw new RuntimeException("获取默认地址列表失败");
        }
        return address;
    }

    @Override
    public int saveAddress(TbAddress address) {
        setOneDefault(address);  // 设置地址的唯一默认

        if (addressMapper.insert(address) != 1) {
            throw new RuntimeException("添加地址失败");
        }
        return 1;
    }

    @Override
    public int updateAddress(TbAddress address) {
        setOneDefault(address);

        if (addressMapper.updateByPrimaryKey(address) != 1) {
            throw new RuntimeException("更新地址失败");
        }
        return 1;
    }

    @Override
    public int removeAddress(TbAddress tbAddress) {
        if (addressMapper.deleteByPrimaryKey(tbAddress.getAddressId()) != 1) {
            throw new RuntimeException("删除地址失败");
        }
        return 1;
    }

    /**
     * 设置唯一默认
     */
    private void setOneDefault(TbAddress address) {
        if (address.getIsDefault()) {  // 如果该地址设置为默认，则将所有地址取消默认
            TbAddressExample example = new TbAddressExample();
            TbAddressExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(address.getUserId());
            List<TbAddress> list = addressMapper.selectByExample(example);

            for (TbAddress addr : list) {
                addr.setIsDefault(false);
                addressMapper.updateByPrimaryKey(addr);
            }
        }
    }
}
