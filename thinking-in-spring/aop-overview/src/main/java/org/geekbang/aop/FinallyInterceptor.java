package org.geekbang.aop;

import java.lang.reflect.Method;

public interface FinallyInterceptor {
    /**
     * 最终执行
     * @param proxy
     * @param method
     * @param args
     * @param result
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, String result);
}
