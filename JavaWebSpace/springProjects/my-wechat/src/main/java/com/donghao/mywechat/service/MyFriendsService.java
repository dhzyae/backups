package com.donghao.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donghao.mywechat.model.MyFriends;

import java.util.List;

public interface MyFriendsService extends IService<MyFriends> {
    Integer insert(MyFriends myFriends);

    List<MyFriends> getListById(Integer userId);
}
