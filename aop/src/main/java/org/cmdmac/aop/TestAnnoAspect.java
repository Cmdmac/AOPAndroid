package org.cmdmac.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.cmdmac.aop.impl.AsyncAspect;
import org.cmdmac.aop.utils.AOPConfig;

@Aspect
public class TestAnnoAspect {

//    @Pointcut("@annotation(org.cmdmac.aspect.annotation.Async)")
    public void pointcut() {

    }

//    @Before("pointcut()")
    public void before(JoinPoint point) {
        Log.w(AsyncAspect.class.getSimpleName(), "@Before");
    }

//    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.w(AsyncAspect.class.getSimpleName(), "@Around");
        //joinPoint.proceed();// 目标方法执行完毕
        AOPConfig.getASyncHandler().run(joinPoint);
    }

//    @After("pointcut()")
    public void after(JoinPoint point) {
        Log.w(AsyncAspect.class.getSimpleName(), "@After");
    }

//    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point, Object returnValue) {
        Log.w(AsyncAspect.class.getSimpleName(), "@AfterReturning");
    }

//    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.w(AsyncAspect.class.getSimpleName(), "@afterThrowing");
        Log.w(AsyncAspect.class.getSimpleName(), "ex = " + ex.getMessage());
    }

}
