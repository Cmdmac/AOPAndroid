package org.cmdmac.aop.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class AOPConfig {
    public static interface IASyncHandler {
        public void run(ProceedingJoinPoint joinPoint);
    }

    private static IASyncHandler sAsyncHandler;
    public static IASyncHandler getASyncHandler() {
        return sAsyncHandler;
    }
    public static void setASyncHandler(IASyncHandler procer) {
        sAsyncHandler = procer;
    }
//    private static ThreadPoolExecutor sThreadPool;
//    public static void setAsyncThreadPool(ThreadPoolExecutor threadPoolExecutor) {
//        if (sThreadPool == null) {
//            sThreadPool = threadPoolExecutor;
//        }
//    }
//
//    public static ThreadPoolExecutor asyncThreadPool() {
//        if (sThreadPool == null) {
//            sThreadPool = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
//
//        }
//        return sThreadPool;
//    }
}
