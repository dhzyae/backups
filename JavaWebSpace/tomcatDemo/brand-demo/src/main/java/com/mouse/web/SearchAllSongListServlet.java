package com.mouse.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mouse.pojo.MusicLibrary;
import com.mouse.service.MusicLibraryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * 搜索所有歌单的 Servlet
 */
@WebServlet(name = "SearchAllSongListServlet", value = "/searchAllSongListServlet")
public class SearchAllSongListServlet extends HttpServlet {

    MusicLibraryService musicLibraryService = new MusicLibraryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        List<MusicLibrary> musicLibraries = musicLibraryService.searchAllSongListByUserId(Integer.valueOf(userId));

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (musicLibraries.size() > 1) {
            writer.write(JSONArray.toJSONString(musicLibraries));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
