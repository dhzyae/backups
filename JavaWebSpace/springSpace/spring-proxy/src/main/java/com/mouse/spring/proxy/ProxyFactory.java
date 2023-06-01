package com.mouse.spring.proxy;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        /**
         * ClassLoader loader：指定加载动态生成的代理类的类加载对象
         * Class<?>[] interfaces：获取目标的对象的所有接口的class对象的数组
         * InvocationHandler h：设置代理类中的抽象方法
         */
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler h = (proxy, method, args) -> {
            System.out.printf("日志，方法：%s，参数：%s\n", method.getName(), Arrays.toString(args));
            Object result = method.invoke(target, args);
            System.out.printf("日志，方法：%s，结果：%s\n", method.getName(), result);
            return result;
        };
        return Proxy.newProxyInstance(classLoader, interfaces, h);
    }

}
