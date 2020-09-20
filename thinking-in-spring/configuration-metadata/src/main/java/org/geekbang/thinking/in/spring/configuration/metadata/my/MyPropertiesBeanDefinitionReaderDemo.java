package org.geekbang.thinking.in.spring.configuration.metadata.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.util.Random;

public class MyPropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //中文乱码
//        String location = "classpath:/META-INF/user-bean-definitions.properties";
//        beanDefinitionReader.loadBeanDefinitions(location);
        //第一种 去掉classpath
        String location = "/META-INF/user-bean-definitions.properties";
//        Resource resource = new ClassPathResource(location);
//        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        // 第二种 Properties 资源加载默认通过 ISO-8859-1，实际存储 UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过指定的 ClassPath 获取 Resource 对象
        Resource resource = resourceLoader.getResource(location);
        // 转换成带有字符编码 EncodedResource 对象
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");

        int n = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        System.out.println("加载的beanDefinition数量 : " + n);

    }
}
