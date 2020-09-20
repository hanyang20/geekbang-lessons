package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class MyURLStreamHandlerFactory implements URLStreamHandlerFactory {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("x".equals(protocol)){
            return new Handler();
        }

        return null;
    }
}
