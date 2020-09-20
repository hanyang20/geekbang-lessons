package org.geekbang.thinking.in.spring.resource.my;

import com.sun.xml.internal.ws.api.ResourceLoader;
import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 * @since
 */
public class MyEncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args)  throws IOException {
        //path
        String localPath = System.getProperty("user.dir")+"\\resource\\src\\main\\java\\org\\geekbang\\thinking\\in\\spring\\resource\\my\\MyEncodedFileSystemResourceDemo.java";
        System.out.println(localPath);
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(localPath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
