package com.mouse.spring.proxy;

public class CalculatorStaticProxy implements Calculator {

    private CalculatorImpl target;

    public CalculatorStaticProxy(CalculatorImpl target) {
        this.target = target;
    }

    @Override
    public int add(int i, int j) {
        System.out.printf("日志，方法：add，参数：%d，%d\n", i, j);
        int result = target.add(1, j);
        System.out.printf("日志，方法：add，结果：%d", result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.printf("日志，方法：sub，参数：%d，%d\n", i, j);
        int result = target.sub(1, j);
        System.out.printf("日志，方法：sub，结果：%d", result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.printf("日志，方法：mul，参数：%d，%d\n", i, j);
        int result = target.mul(1, j);
        System.out.printf("日志，方法：mul，结果：%d", result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.printf("日志，方法：div，参数：%d，%d\n", i, j);
        int result = target.div(1, j);
        System.out.printf("日志，方法：div，结果：%d", result);
        return result;
    }
}
