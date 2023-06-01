package com.donghao.mywechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donghao.mywechat.mapper.MyAddressMapper;
import com.donghao.mywechat.model.MyAddress;
import com.donghao.mywechat.service.MyAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyAddressServiceImpl extends ServiceImpl<MyAddressMapper, MyAddress> implements MyAddressService {
    @Override
    public Integer insert(MyAddress myAddress) {
        return baseMapper.insert(myAddress);
    }

    @Override
    public List<MyAddress> getListById(Integer userId) {
        LambdaQueryWrapper<MyAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MyAddress::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }
}
