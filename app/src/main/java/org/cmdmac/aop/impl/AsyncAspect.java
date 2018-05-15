package org.cmdmac.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.cmdmac.aop.utils.AOPConfig;

@Aspect
public class AsyncAspect {
    @Pointcut("@within(org.cmdmac.aop.annotation.Async)||@annotation(org.cmdmac.aop.annotation.Async)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
//        Log.w(AsyncAspect.class.getSimpleName(), "@Before");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("@Around");

        AOPConfig.getASyncHandler().run(joinPoint);
//        joinPoint.proceed();// 目标方法执行完毕
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
//        Log.w(AsyncAspect.class.getSimpleName(), "@After");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point, Object returnValue) {
//        System.out.println("@AfterReturning");
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
//        System.out.println("@afterThrowing");
//        System.out.println("ex = " + ex.getMessage());
    }
}
