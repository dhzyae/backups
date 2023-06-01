package com.donghao.mywechat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.donghao.mywechat.mapper.UserMapper;
import com.donghao.mywechat.model.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testQuery() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickname, "董浩");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

}
