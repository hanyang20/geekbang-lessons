package org.geekbang.aop;

import org.geekbang.DefaultEchoService;
import org.geekbang.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        Object proxy = Proxy.newProxyInstance(contextClassLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {

                    //前置
//                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
//                        @Override
//                        public Object before(Object proxy, Method method, Object[] args) {
//                            return System.currentTimeMillis();
//                        }
//                    };
                    BeforeInterceptor beforeInterceptor = (proxy1, method1, args1) -> {
                        return System.currentTimeMillis();
                    };

                    long endTime = 0L;
                    long startTime = 0L;
                    String result = "";
                    try {
                        //前置拦截
                        startTime = (long) beforeInterceptor.before(proxy, method, args);
                        //目标对象
                        DefaultEchoService defaultEchoService = new DefaultEchoService();
                        result = defaultEchoService.echo((String) args[0]);

                        //后置拦截
//                        AfterInterceptor afterInterceptor = new AfterInterceptor() {
//                            @Override
//                            public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
//                                return System.currentTimeMillis();
//                            }
//                        };

                        AfterInterceptor afterInterceptor = (proxy1, method1, args1, returnResult) -> {
                            return System.currentTimeMillis();
                        };

                        endTime = (long) afterInterceptor.after(proxy, method, args, result);
                    } catch (Exception e) {

//                        new ExceptionInterceptor() {
//                            @Override
//                            public Object before(Object proxy, Method method, Object[] args, Throwable throwable) {
//                                return null;
//                            }
//                        };
                        ExceptionInterceptor ExceptionInterceptor = (proxy1, method1, args1, throwable) -> {
                            return null;
                        };

                    } finally {
                        long finalEndTime = endTime;
                        long finalStartTime = startTime;
//                        FinallyInterceptor finallyInterceptor = new FinallyInterceptor() {
//                            @Override
//                            public Object finalize(Object proxy, Method method, Object[] args, String result) {
//                                long constTime = finalEndTime - finalStartTime;
//                                return constTime;
//                            }
//                        };

                        FinallyInterceptor finallyInterceptor = (proxy1, method1, args1, throwable) -> {
                            return finalEndTime - finalStartTime;
                        };
                        long constTime = (long) finallyInterceptor.finalize(proxy, method, args, result);

                        System.out.println("消耗时间: " + constTime + " ms.");
                    }
                }

                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("123");
    }
}
