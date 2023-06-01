package com.mouse.web;

import com.alibaba.fastjson.JSONObject;
import com.mouse.pojo.Moment;
import com.mouse.service.MomentService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "ShareSongServlet", value = "/shareSongServlet")
public class ShareSongServlet extends HttpServlet {

    private final MomentService momentService = new MomentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Moment moment = new Moment();
        try {
            BeanUtils.populate(moment, request.getParameterMap());
            System.out.println(JSONObject.toJSONString(moment));
            int i = momentService.addMoment(moment);

            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (i > 0) {
                writer.write("添加成功");
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
