package com.mouse.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mouse.pojo.MusicLibrary;
import com.mouse.pojo.User;
import com.mouse.service.MusicLibraryService;
import com.mouse.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户注册的Servlet
 * 添加新用户后，自动添加一个默认歌单（我喜欢的音乐）
 */
@WebServlet(name = "RegisterServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final MusicLibraryService musicLibraryService = new MusicLibraryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int i = userService.addUser(user);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (i > 0) {
            MusicLibrary musicLibrary = new MusicLibrary();
            musicLibrary.setUserId(user.getUserId());
            musicLibrary.setSongListTitle("我喜欢的音乐");
            int i1 = musicLibraryService.addSongList(musicLibrary);
            if (i1 > 0) {
                response.setStatus(200);
                JSONArray objects = new JSONArray();
                objects.add(user);
                objects.add(musicLibrary);
                writer.write(objects.toJSONString());
            }
        } else {
            response.setStatus(502);
            writer.write("注册失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
