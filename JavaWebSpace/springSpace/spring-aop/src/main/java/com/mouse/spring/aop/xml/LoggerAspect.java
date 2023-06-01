package com.mouse.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LoggerAspect {

    public void pointCut() {
    }

    public void beforeAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        //获取连接点所有对应方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.printf("LoggerAspect，方法：%s，参数：%s\n", signature.getName(), Arrays.toString(args));
    }

    public void afterAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，执行完毕\n", signature.getName());
    }

    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，结果：%s\n", signature.getName(), result);
    }

    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，异常通知，异常：%s\n", signature.getName(), ex);
    }

    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("环绕通知-->前置通知");
            //表示目标对象方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->返回通知");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("环绕通知-->异常通知");
        } finally {
            System.out.println("环绕通知-->后置通知");
        }
        return result;
    }

}
