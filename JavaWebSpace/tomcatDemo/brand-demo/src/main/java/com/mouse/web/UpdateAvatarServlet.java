package com.mouse.web;

import com.mouse.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户更新头像的Servlet
 */
@WebServlet(name = "UpdateAvatarServlet", value = "/updateAvatarServlet")
public class UpdateAvatarServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String avatarUrl = request.getParameter("avatarUrl");

        int i = userService.updateAvatar(Integer.valueOf(userId), avatarUrl);

//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = response.getWriter();
        if (i > 0) {
            response.setStatus(200);
        } else {
            // 未修改
            response.setStatus(304);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
