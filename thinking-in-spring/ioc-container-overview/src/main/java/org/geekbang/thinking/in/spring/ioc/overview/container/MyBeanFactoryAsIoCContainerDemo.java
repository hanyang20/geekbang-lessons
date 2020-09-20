package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class MyBeanFactoryAsIoCContainerDemo {


    public static void main(String[] args) {

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(location);

//        User user = defaultListableBeanFactory.getBean(User.class);
//        System.out.println("user"+user);
        lookupCollectionByType(defaultListableBeanFactory);
    }

    public static void lookupCollectionByType(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;

            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(beansWithAnnotation);

        }
    }
}
