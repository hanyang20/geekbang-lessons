package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.constructorinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class MyAutoWiringConstructorDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String location = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";

        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder userHolder = defaultListableBeanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
