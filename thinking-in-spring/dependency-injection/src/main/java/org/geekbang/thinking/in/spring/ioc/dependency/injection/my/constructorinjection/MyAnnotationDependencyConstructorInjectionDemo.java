package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.constructorinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.my.setterinjection.MyAnnotationDependencyInjectionDemo;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class MyAnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationDependencyConstructorInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}
