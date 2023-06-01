package com.mouse.web;

import com.alibaba.fastjson.JSONArray;
import com.mouse.pojo.SongList;
import com.mouse.service.SongListService;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SearchListDetailServlet", value = "/searchListDetailServlet")
public class SearchListDetailServlet extends HttpServlet {

    private final SongListService songListService = new SongListService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String songListId = request.getParameter("songListId");
        List<SongList> songLists = songListService.searchListDetail(songListId);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (songLists.size() > 0) {
            writer.write(JSONArray.toJSONString(songLists));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
