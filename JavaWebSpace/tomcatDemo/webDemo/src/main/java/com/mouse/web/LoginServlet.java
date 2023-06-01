package com.mouse.web;

import com.mouse.mapper.UserMapper;
import com.mouse.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 调用 MyBatis 完成查询
        // 获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取 Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        User user = userMapper.select(username, password);
        // 释放资源
        sqlSession.close();

        PrintWriter writer = response.getWriter();
        // 判断 user 是否为 null
        if (user != null) {//登录成功
            writer.write(username);
        } else {//登录失败

        }






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
