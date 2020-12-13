package org.geekbang;

public class ProxyEchoService implements EchoService {

    private EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        long startTime = System.currentTimeMillis();

        String mes = echoService.echo(message);
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间: " + (endTime - startTime) + " ms.");
        return mes;
    }
}
