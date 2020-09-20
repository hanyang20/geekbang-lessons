package org.geekbang.thinking.in.spring.resource.my;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
/**
 * 带有字符编码的 {@link FileSystemResource} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FileSystemResource
 * @see EncodedResource
 * @since
 */
public class MyEncodedFileSystemResourceDemo {

    public static void main(String[] args)  throws IOException{
        //path
//        String localPath = System.getProperty("user.dir")+"\\resource\\src\\main\\java\\org\\geekbang\\thinking\\in\\spring\\resource\\my\\MyEncodedFileSystemResourceDemo.java";
        String localPath = System.getProperty("user.dir")+"\\resource\\src\\main\\resources\\META-INF\\default.properties";
        System.out.println(localPath);

        //file
        File file = new File(localPath);
        Resource resource = new FileSystemResource(localPath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
