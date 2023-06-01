package com.mouse.web;

import com.alibaba.fastjson.JSONObject;
import com.mouse.pojo.User;
import com.mouse.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户登录的Servlet
 */
@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.selectUser(username, password);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (user != null) {
            response.setStatus(200);
            writer.write(JSONObject.toJSONString(user));
        } else {
            response.setStatus(502);
            writer.write("用户名或密码不存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
