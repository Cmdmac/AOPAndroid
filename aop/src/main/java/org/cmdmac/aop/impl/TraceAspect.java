package org.cmdmac.aop.impl;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cmdmac.aop.annotation.Trace;

@Aspect
public class TraceAspect {
//    @Pointcut("execution(@org.cmdmac.aop.annotation.Trace * *(..))")
    @Pointcut("@annotation(org.cmdmac.aop.annotation.Trace)")
    public void pointcut() {

    }

//    @Around("pointcut() && @annotation(trace)")
    @Around("@annotation(org.cmdmac.aop.annotation.Trace)")
    public Object around(ProceedingJoinPoint joinPoint, Trace trace) throws Throwable {
//        System.out.println("@Around");

        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
//        System.out.println(end - start);
//        System.out.println(trace.pkg());
        String pkg = trace.pkg();
        if (pkg != null && pkg.length() > 0) {
            String str = String.format("%s.%s cost %fms", new Object[]{pkg, joinPoint.getSignature().getName(), (end - start) / 1000000.0});
            Log.w(TraceAspect.class.getSimpleName(), str);
        } else {
            String str = String.format("%s cost %fms", new Object[]{joinPoint.getSignature().toShortString(), (end - start) / 1000000.0});
            Log.w(TraceAspect.class.getSimpleName(), str);
        }

        return result;
    }
}
