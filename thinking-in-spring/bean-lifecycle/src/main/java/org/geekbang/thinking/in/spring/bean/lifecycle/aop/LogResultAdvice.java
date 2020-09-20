package org.geekbang.thinking.in.spring.bean.lifecycle.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogResultAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("结束方法: " + method.getName() + ", 方法返回：" + returnValue);

    }
}
