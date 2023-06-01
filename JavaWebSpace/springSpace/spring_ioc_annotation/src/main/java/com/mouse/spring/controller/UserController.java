package com.mouse.spring.controller;

import com.mouse.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void saveUser() {
        userService.saveUser();
    }

}
