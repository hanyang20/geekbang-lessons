package org.geekbang.aop;

import java.lang.reflect.Method;

public interface AfterInterceptor {

    /**
     * 后置动作
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);

}
