package org.geekbang.aop;

import java.lang.reflect.Method;

public interface BeforeInterceptor {
    /**
     * 前置动作
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
