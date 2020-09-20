package org.geekbang.thinking.in.spring.resource.my;

import org.apache.commons.io.IOUtils;
import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 对象示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 * @since
 */
public class MyInjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource propertiesResource;

    @Value("${user.dir}")
    private String currentPath;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("classpath*:/META-INF/*.properties")
    private List<Resource> list;


    @PostConstruct
    public void init() throws IOException {
        System.out.println(ResourceUtils.getContent(propertiesResource));
        System.out.println("==================");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("==================");
        System.out.println(list);
//        for (Resource r : list) {
//            System.out.println(ResourceUtils.getContent(r));
//        }

        System.out.println("==================");
        System.out.println(currentPath);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyInjectingResourceDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }
}
