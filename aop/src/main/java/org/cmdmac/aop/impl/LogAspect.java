package org.cmdmac.aop.impl;

import android.text.TextUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cmdmac.aop.annotation.Log;

/**
 * Created by Tony Shen on 16/3/22.
 */
@Aspect
public class LogAspect {

    @Pointcut("within(@org.cmdmac.aop.annotation.Log *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@org.cmdmac.aop.annotation.Log * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@org.cmdmac.aop.annotation.Log *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("(method() || constructor()) && @annotation(log)")
    public Object logAndExecute(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
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