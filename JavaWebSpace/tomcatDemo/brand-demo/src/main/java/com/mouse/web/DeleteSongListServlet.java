package com.mouse.web;

import com.mouse.service.MusicLibraryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteSongListServlet", value = "/deleteSongListServlet")
public class DeleteSongListServlet extends HttpServlet {

    private final MusicLibraryService musicLibraryService = new MusicLibraryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String songListId = request.getParameter("songListId");

        int i = musicLibraryService.deleteSongList(songListId);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (i > 0) {
            writer.write("删除成功");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
