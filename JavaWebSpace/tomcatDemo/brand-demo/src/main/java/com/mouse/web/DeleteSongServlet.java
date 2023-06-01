package com.mouse.web;

import com.mouse.service.SongListService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteSongServlet", value = "/deleteSongServlet")
public class DeleteSongServlet extends HttpServlet {

    private final SongListService songListService = new SongListService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String songListId = request.getParameter("songListId");
        String songId = request.getParameter("songId");

        System.out.println(songId);

        int i = songListService.deleteSong(songListId, songId);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (i > 0) {
            writer.write("删除成功");
        } else {
            response.setStatus(204);
            writer.write("删除失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
