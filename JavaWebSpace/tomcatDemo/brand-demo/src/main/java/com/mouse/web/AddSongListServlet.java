package com.mouse.web;

import com.alibaba.fastjson.JSONObject;
import com.mouse.pojo.MusicLibrary;
import com.mouse.service.MusicLibraryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加歌单的 Servlet
 */
@WebServlet(name = "AddSongListServlet", value = "/addSongListServlet")
public class AddSongListServlet extends HttpServlet {
    private final MusicLibraryService musicLibraryService = new MusicLibraryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String songListTitle = request.getParameter("songListTitle");

        MusicLibrary musicLibrary = new MusicLibrary();
        musicLibrary.setUserId(Integer.valueOf(userId));
        musicLibrary.setSongListTitle(songListTitle);

        System.out.println(JSONObject.toJSONString(musicLibrary));
        int i = musicLibraryService.addSongList(musicLibrary);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (i > 0) {
            writer.write(JSONObject.toJSONString(musicLibrary));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
