package com.donghao.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donghao.mywechat.model.Friends;
import com.donghao.mywechat.model.MyFriends;

import java.util.List;

public interface FriendsService extends IService<Friends> {
    Integer add(Friends friends);

    List<Friends> getFriendsList(List<MyFriends> myFriendsList);
}
