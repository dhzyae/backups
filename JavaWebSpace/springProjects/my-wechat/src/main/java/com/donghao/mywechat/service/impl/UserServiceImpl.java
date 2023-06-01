package com.donghao.mywechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donghao.mywechat.common.result.Result;
import com.donghao.mywechat.mapper.UserMapper;
import com.donghao.mywechat.model.User;
import com.donghao.mywechat.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    //登录验证
    @Override
    public List<User> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhoneNumber, user.getPhoneNumber());
        wrapper.eq(User::getPassword, user.getPassword());
        return baseMapper.selectList(wrapper);
    }
}
