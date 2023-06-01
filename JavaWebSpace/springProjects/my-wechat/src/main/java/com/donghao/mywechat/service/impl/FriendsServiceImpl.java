package com.donghao.mywechat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donghao.mywechat.mapper.FriendsMapper;
import com.donghao.mywechat.model.Friends;
import com.donghao.mywechat.model.MyFriends;
import com.donghao.mywechat.service.FriendsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements FriendsService {

    @Override
    public Integer add(Friends friends) {
        return baseMapper.insert(friends);
    }

    @Override
    public List<Friends> getFriendsList(List<MyFriends> myFriendsList) {
        List<Integer> friendsIdList = myFriendsList.stream()
                .map(MyFriends::getFriendId)
                .collect(Collectors.toList());
        return baseMapper.selectBatchIds(friendsIdList);
    }
}
