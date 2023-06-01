package com.mouse.spring.test;

import com.mouse.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {

    @Test
    public void TestScope() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-scope.xml");
        Student student = ioc.getBean(Student.class);
        Student student1 = ioc.getBean(Student.class);
        System.out.println(student == student1);
    }

}
