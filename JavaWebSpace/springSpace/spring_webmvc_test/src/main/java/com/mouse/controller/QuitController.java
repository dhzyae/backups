package com.mouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuitController {

    @RequestMapping("/show")
    public String show() {
        System.out.println("show running...");
        return "index.jsp";
    }

}
