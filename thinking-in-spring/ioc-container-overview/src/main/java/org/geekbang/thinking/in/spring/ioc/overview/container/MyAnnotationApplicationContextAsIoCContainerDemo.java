package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class MyAnnotationApplicationContextAsIoCContainerDemo {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationApplicationContextAsIoCContainerDemo.class);

        applicationContext.refresh();

        lookupCollectionByType(applicationContext);
        User bean = (User) applicationContext.getBean("user");
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();

        user.setId(1L);
        user.setName("张三");
        return user;
    }

    public static void lookupCollectionByType(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;

            Map<String, User> beansWithAnnotation = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beansWithAnnotation);

        }
    }

}
