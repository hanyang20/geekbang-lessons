package org.geekbang.thinking.in.spring.ioc.dependency.source.my;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties", encoding = "UTF-8")
public class MyExternalConfigurationDependencySourceDemo {


    @Value("${demo.id:-1}")
    private Long id;

    @Value(value = "${demo.name:zhangsan}")
    private String name;

    @Value("${demo.resource:classpath://META-INF/default.properties}")
    private Resource resource;


    //或者是通过addBeanFactoryPostProcessor
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(MyExternalConfigurationDependencySourceDemo.class);

        applicationContext.refresh();

        MyExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(MyExternalConfigurationDependencySourceDemo.class);

        System.out.println("id:"+demo.id);
        System.out.println("name:"+demo.name);
        System.out.println("resource: "+demo.resource);
        applicationContext.close();
    }
}
