package org.geekbang;

import org.omg.PortableInterceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        Object proxy = Proxy.newProxyInstance(contextClassLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
                return proxyEchoService.echo((String) args[0]);
            }
        });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("123");

    }
}
