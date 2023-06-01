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
@MapperScan("com.donghao.mywechat.mapper")
class MyWechatApplicationTests {

    @Test
    void contextLoads() {
    }

}
