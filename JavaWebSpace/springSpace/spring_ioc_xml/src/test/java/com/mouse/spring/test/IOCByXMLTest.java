package com.mouse.spring.test;

import com.mouse.spring.pojo.Clazz;
import com.mouse.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCByXMLTest {

    /**
     * 获取Bean的三种方式：
     *  1. 根据bean的id获取
     *  2. 根据bean的类型获取（要求IOC容器中有且只有一个类型匹配的bean）
     *     ps: (若没有一个类型匹配的bean，此时抛出异常：NoSuchBeanDefinitionException
     *          若有多个类型匹配的bean，此时抛出异常：NoUniqueBeanDefinitionException)
     *  3. 根据id和类型获取
     *  结论：
     *      根据类型来获取bean时，在满足bean唯一性的前提下，其实只看: [对象 instanceof 指定类型] 的返回结果
     *      只要返回的是true就可以认为和类型匹配，能过获取到
     *      即通过bean的类型，bean所继承的类的类型，bean所实现的接口类型都可以获取bean
     */

    @Test
    public void testDI() {
        //获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        Student studentTwo = ioc.getBean("studentSeven", Student.class);
        System.out.println(studentTwo);
        /*Clazz clazzOne = ioc.getBean("clazzOne", Clazz.class);
        System.out.println(clazzOne);*/
    }

    @Test
    public void testIOC() {
        //获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        //根据id获取bean
//        Student studentOne = (Student) ioc.getBean("studentOne");
        //根据类型获取bean
//        Student student = ioc.getBean(Student.class);
        //根据类型和id获取bean
        Student studentOne = ioc.getBean("studentOne", Student.class);
        System.out.println(studentOne);
    }
}
