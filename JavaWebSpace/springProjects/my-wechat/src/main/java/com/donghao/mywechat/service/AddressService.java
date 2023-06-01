package com.donghao.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donghao.mywechat.model.Address;
import com.donghao.mywechat.model.MyAddress;

import java.util.List;

public interface AddressService extends IService<Address> {

    Integer add(Address address);

    List<Address> getAddressList(List<MyAddress> myAddressList);
}
