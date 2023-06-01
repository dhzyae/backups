package com.donghao.mywechat.controller;

import com.donghao.mywechat.common.result.Result;
import com.donghao.mywechat.model.Friends;
import com.donghao.mywechat.model.MyFriends;
import com.donghao.mywechat.service.FriendsService;
import com.donghao.mywechat.service.MyFriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "好友管理接口")
@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private MyFriendsService myFriendsService;

    @ApiOperation("添加好友")
    @PostMapping("/add/{userId}")
    public Result add(@PathVariable Integer id, @RequestBody Friends friends) {
        Integer rows = friendsService.add(friends);
        if (rows > 0) {
            int result = myFriendsService.insert(new MyFriends(null, id, friends.getId()));
            return result > 0 ? Result.ok() : Result.fail();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询好友")
    @GetMapping("/get/{userId}")
    public Result get(@PathVariable Integer userId) {
        List<MyFriends> myFriendsList = myFriendsService.getListById(userId);
        if (myFriendsList.size() > 0) {
            List<Friends> friendsList = friendsService.getFriendsList(myFriendsList);
            return friendsList.size() > 0 ?
                    Result.ok(friendsList) : Result.fail();
        } else {
            return Result.fail();
        }
    }
}
