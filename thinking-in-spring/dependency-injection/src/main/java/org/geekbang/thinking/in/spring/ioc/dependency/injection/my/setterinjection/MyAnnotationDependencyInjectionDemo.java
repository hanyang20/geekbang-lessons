package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.setterinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class MyAnnotationDependencyInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){// superUser -> primary = true
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
//    @Bean
//    public UserHolder userHolder(User user){
//        return new UserHolder(user);
//    }
}
