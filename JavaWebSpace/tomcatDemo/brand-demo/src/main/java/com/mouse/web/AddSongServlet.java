package com.mouse.web;

import com.alibaba.fastjson.JSONObject;
import com.mouse.pojo.SongList;
import com.mouse.pojo.User;
import com.mouse.service.SongListService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

/**
 * 添加歌曲到歌单的 Servlet
 */
@WebServlet(name = "AddSongServlet", value = "/addSongServlet")
public class AddSongServlet extends HttpServlet {

    private final SongListService songListService = new SongListService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongList songList = new SongList();
        try {
            BeanUtils.populate(songList, request.getParameterMap());
//            System.out.println(JSONObject.toJSONString(songList));
            int i = songListService.addSong(songList);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (i > 0) {
                response.setStatus(200);
            } else {
                response.setStatus(204);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
