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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

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
        User u = userMapper.selectByUsername(username);

        // 判断用户对象是否为空
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (u == null) {// 用户名不存在，添加新用户
            userMapper.add(user);
            // 提交事务
            sqlSession.commit();
            // 释放资源
            sqlSession.close();
            writer.write("123");
        } else {// 用户名存在，给出提示信息
            writer.write("用户名已存在！！！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
