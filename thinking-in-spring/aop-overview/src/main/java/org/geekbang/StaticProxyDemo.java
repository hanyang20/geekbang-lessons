package org.geekbang;

public class StaticProxyDemo {

    public static void main(String[] args) {

        //静态代理
        //实现/继承 + 组合的方式实现
        ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());

        proxyEchoService.echo("hello world");

    }
}
