package com.donghao.mywechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.donghao.mywechat.mapper ")
public class MyWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWechatApplication.class, args);
    }

}
