package sun.net.www.protocol.x;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 * X {@link URLConnection} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class XURLConnection extends URLConnection {


    private ClassPathResource resource;

    // x:/META-INF/default.properties
    protected XURLConnection(URL url) {
        super(url);
        String path = url.getPath();//    path = /META-INF/default.properties
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = resource.getInputStream();

        return inputStream;
    }
}
