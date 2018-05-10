package org.cmdmac.aspect.impl;

import android.text.TextUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.cmdmac.aspect.annotation.Log;
import org.cmdmac.aspect.annotation.Trace;

import java.util.Arrays;

/**
 * Created by Tony Shen on 16/3/22.
 */
@Aspect
public class LogAspect {

    @Pointcut("execution(@org.cmdmac.aspect.annotation.Log * *(..))")
    public void pointcut() {

    }

    @Around("pointcut() && @annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        StringBuilder sb = new StringBuilder();
        String msg = log.msg();
        if (TextUtils.isEmpty(msg)) {
            msg = joinPoint.getSignature().toShortString();
            sb.append(msg);
        }
        if (log.showParams()) {
            Object[] args = joinPoint.getArgs();
            if (args != null) {
                for (Object arg : args) {
                    sb.append(arg.getClass().getSimpleName()).append("=");
                    sb.append(arg.toString())
                            .append(',');
                }
            }
        }
        android.util.Log.w(LogAspect.class.getSimpleName(), sb.toString());
        return joinPoint.proceed();
    }


//    }
}