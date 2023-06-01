package com.donghao.mywechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donghao.mywechat.mapper.AddressMapper;
import com.donghao.mywechat.mapper.MyAddressMapper;
import com.donghao.mywechat.model.Address;
import com.donghao.mywechat.model.MyAddress;
import com.donghao.mywechat.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public Integer add(Address address) {
        return baseMapper.insert(address);
    }

    //根据地址id查询地址
    @Override
    public List<Address> getAddressList(List<MyAddress> myAddressList) {
        List<Integer> addressIdList = myAddressList.stream()
                .map(MyAddress::getAddressId)
                .collect(Collectors.toList());
        return baseMapper.selectBatchIds(addressIdList);
    }

}
