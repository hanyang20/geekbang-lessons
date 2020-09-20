package org.geekbang.thinking.in.spring.configuration.metadata.my.ExtensibleXmlAuthoring;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

public class MyExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        //中文乱码
        String location = "/META-INF/users-context-my.xml";
        int n = beanDefinitionReader.loadBeanDefinitions(location);
        User user = beanFactory.getBean(User.class);
        System.out.println("加载的beanDefinition数量 : " + n);
        System.out.println(user);
    }
}
