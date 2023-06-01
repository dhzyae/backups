package com.donghao.mywechat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.donghao.mywechat.mapper.UserMapper;
import com.donghao.mywechat.model.User;
import com.donghao.mywechat.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo2 {

    @Autowired
    private UserService service;

    @Test
    public void getAll() {
        List<User> list = service.list();
        System.out.println(list);
    }

}
