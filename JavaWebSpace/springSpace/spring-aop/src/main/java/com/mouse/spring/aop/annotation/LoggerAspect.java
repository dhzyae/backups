package com.mouse.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 1. 在切面中，需要指定的注解将方法标识为通知方法
 * {@code @Before：前置通知，在目标对象方法执行之前执行}
 * {@code @After：后置通知，在目标对象方法的finally字句中执行}
 * {@code @AfterReturning：返回通知，在目标对象方法返回值之后执行}
 * {@code @AfterThrowing：异常通知，在目标对象方法的catch字句中执行}
 * <p>
 * 2. 切入点表达式：设置在标识通知的注解的value属性中
 * execution(public int com.mouse.spring.aop.annotation.CalculatorImpl.add(int,int))
 * execution(* com.mouse.spring.aop.annotation.CalculatorImpl.*(..))
 * 第一个 * 表示任意的访问修饰符和返回值类型
 * 第二个 * 表示类中的任意的方法
 * .. 表示任意的参数列表
 * 类的地方也可以使用 * ，表示包下的所有的类
 * <p>
 * 3. 重用切入点表达式
 * {@code @Pointcut("execution(*  com.mouse.spring.aop.annotation.CalculatorImpl.*(..))")}
 * public void pointCut() {}
 * 使用方式：@Before("pointCut()")
 * <p>
 * 4. 获取连接点的信息
 * 在通知方法的参数位置，设置joinPoint类型的参数，就可以获取连接点所对应方法的信息
 * //获取连接点所有对应方法的签名信息
 * Signature signature = joinPoint.getSignature();
 * //获取连接点所有对应方法的参数
 * Object[] args = joinPoint.getArgs();
 * <p>
 * 5. 切面的优先级
 * 可以通过@Order注解的value属性设置优先级，默认值Integer的最大值
 * {@code @Order注解的value属性值越小，优先级越高}
 */

@Component
@Aspect//将当前组件标识为切面
public class LoggerAspect {

    @Pointcut("execution(* com.mouse.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut() {
    }

    //    @Before("execution(public int com.mouse.spring.aop.annotation.CalculatorImpl.add(int,int))")
//    @Before("execution(* com.mouse.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("pointCut()")
    public void beforeAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        //获取连接点所有对应方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.printf("LoggerAspect，方法：%s，参数：%s\n", signature.getName(), Arrays.toString(args));
    }

    @After("pointCut()")
    public void afterAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，执行完毕\n", signature.getName());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，结果：%s\n", signature.getName(), result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex) {
        //获取连接点所有对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.printf("LoggerAspect，方法：%s，异常通知，异常：%s\n", signature.getName(), ex);
    }

    @Around("pointCut()")
    //环绕通知的返回值一定要和目标对象的返回值一致
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
