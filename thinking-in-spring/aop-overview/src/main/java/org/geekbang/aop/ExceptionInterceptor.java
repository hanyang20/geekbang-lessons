package org.geekbang.aop;

import java.lang.reflect.Method;

public interface ExceptionInterceptor {

    /**
     * 异常拦截
     * @param proxy
     * @param method
     * @param args
     * @param throwable 异常
     * @return
     */
    Object before(Object proxy, Method method, Object[] args, Throwable throwable);

}
