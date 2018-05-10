package org.cmdmac.aspect.impl;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cmdmac.aspect.utils.AOPConfig;

import java.time.Clock;
import java.util.Timer;

@Aspect
public class TraceAspect {
    @Pointcut("execution(@org.cmdmac.aspect.annotation.Trace * *(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("@Around");

        long start = System.nanoTime();
        joinPoint.proceed();
        long end = System.nanoTime();
//        System.out.println(end - start);
        String str = String.format("%s cost %fms", new Object[] {joinPoint.getSignature().getName(), (end - start) / 1000000.0});
        Log.w(TraceAspect.class.getSimpleName(),  str);
//        joinPoint.proc`eed();// 目标方法执行完毕
    }
}
