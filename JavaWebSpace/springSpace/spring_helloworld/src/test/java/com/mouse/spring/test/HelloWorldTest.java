package com.mouse.spring.test;

import com.mouse.spring.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {

    @Test
    public void test() {
        // 获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取 IOC 容器中的 bean
        Object object = ioc.getBean("hello_world");
        if (object instanceof HelloWorld) {
            HelloWorld helloWorld = (HelloWorld) object;
            helloWorld.sayHello();
        }
    }

}
