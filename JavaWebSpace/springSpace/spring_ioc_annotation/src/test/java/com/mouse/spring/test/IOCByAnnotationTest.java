package com.mouse.spring.test;

import com.mouse.spring.controller.UserController;
import com.mouse.spring.dao.UserDao;
import com.mouse.spring.dao.impl.UserDaoImpl;
import com.mouse.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCByAnnotationTest {

    @Test
    public void testAnnotation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
        UserController userController = ioc.getBean(UserController.class);
        userController.saveUser();
    }

}
