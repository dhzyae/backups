package com.mouse.web;

import com.alibaba.fastjson.JSONArray;
import com.mouse.pojo.Moment;
import com.mouse.service.MomentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SearchAllMomentServlet", value = "/searchAllMomentServlet")
public class SearchAllMomentServlet extends HttpServlet {

    private final MomentService momentService = new MomentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Moment> moments = momentService.selectAllMoment();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (moments.size() > 0) {
            writer.write(JSONArray.toJSONString(moments));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
