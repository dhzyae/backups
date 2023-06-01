package com.mouse.spring.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void testProxy() {
        /*CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
        calculatorStaticProxy.add(1, 1);*/
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1, 1);
    }

}
