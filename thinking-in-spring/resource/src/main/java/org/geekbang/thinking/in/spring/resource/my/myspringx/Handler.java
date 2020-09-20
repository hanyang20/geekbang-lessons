package org.geekbang.thinking.in.spring.resource.my.myspringx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class Handler extends sun.net.www.protocol.x.Handler                                                                                                                               {

    //需要加上 -Djava.protocol.handler.pkgs=org.geekbang.thinking.in.spring.resource
    public static void main(String[] args) throws IOException {
        URL url = new URL("myspringx:/META-INF/production.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));

    }
}
