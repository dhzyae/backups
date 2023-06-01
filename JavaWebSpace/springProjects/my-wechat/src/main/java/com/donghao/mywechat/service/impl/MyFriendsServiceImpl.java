package com.donghao.mywechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donghao.mywechat.mapper.MyFriendsMapper;
import com.donghao.mywechat.model.MyFriends;
import com.donghao.mywechat.service.MyFriendsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFriendsServiceImpl extends ServiceImpl<MyFriendsMapper, MyFriends> implements MyFriendsService {
    @Override
    public Integer insert(MyFriends myFriends) {
        return baseMapper.insert(myFriends);
    }

    @Override
    public List<MyFriends> getListById(Integer userId) {
        LambdaQueryWrapper<MyFriends> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MyFriends::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }
}
