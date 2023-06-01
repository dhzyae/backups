package com.donghao.mywechat.controller;

import com.donghao.mywechat.model.User;
import com.donghao.mywechat.common.result.Result;
import com.donghao.mywechat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result<List<User>> findAll() {
        List<User> list = userService.list();
        return Result.ok(list);
    }

    @ApiOperation("登录验证")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.login(user);
        if (list.size() > 0) {
            return Result.ok(list.get(0));
        }else {
            return Result.fail();
        }
    }



}
