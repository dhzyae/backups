package com.mouse.spring.test;

import com.mouse.spring.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleTest {

    /**
     * 1. 实例化
     * 2. 依赖注入
     * 3. 初始化，需要通过bean的init-method属性指定初始化的方法
     * 4. IOC容器关闭时销毁，需要通过bean的destroy-method属性指定销毁的方法
     * 注意：
     *  若bean的作用域为单例时，生命周期的前三个步骤会在获取IOC容器时执行
     *  若bean的作用域为多例时，生命周期的前三个步骤会在获取bean时执行
     */

    @Test
    public void test() {
        ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
        User user = ioc.getBean(User.class);
        System.out.println(user);
        ioc.close();
    }

}
