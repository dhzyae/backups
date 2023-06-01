package com.donghao.mywechat.controller;

import com.donghao.mywechat.common.result.Result;
import com.donghao.mywechat.model.Address;
import com.donghao.mywechat.model.MyAddress;
import com.donghao.mywechat.service.AddressService;
import com.donghao.mywechat.service.MyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "地址管理接口")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private MyAddressService myAddressService;

    @ApiOperation("添加地址")
    @PostMapping("/add/{userId}")
    public Result add(@PathVariable Integer userId, @RequestBody Address address) {
        Integer rows = addressService.add(address);
        if (rows > 0) {
            int result = myAddressService.insert(new MyAddress(null, userId, address.getId()));
            return result > 0 ? Result.ok() : Result.fail();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询地址")
    @GetMapping("/get/{userId}")
    public Result get(@PathVariable Integer userId) {
        List<MyAddress> myAddressList = myAddressService.getListById(userId);
        if (myAddressList.size() > 0) {
            List<Address> addressList = addressService.getAddressList(myAddressList);
            return addressList.size() > 0 ?
                    Result.ok(addressList) : Result.fail();
        }else {
            return Result.fail();
        }
    }

}
