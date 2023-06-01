package com.donghao.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donghao.mywechat.common.result.Result;
import com.donghao.mywechat.model.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> login(User user);
}
