package com.donghao.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donghao.mywechat.model.Address;
import com.donghao.mywechat.model.MyAddress;

import java.util.List;

public interface MyAddressService extends IService<MyAddress> {
    Integer insert(MyAddress myAddress);

    List<MyAddress> getListById(Integer userId);
}
