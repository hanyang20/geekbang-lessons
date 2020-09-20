package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyObjectProviderDemo {//@Configuration非必须

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupByIfAvailable(applicationContext);

        lookupByStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> iterable = beanProvider;
//        for (String s:iterable) {
//            System.out.println(s);
//        }
//        beanProvider.stream().forEach(s -> {
//            System.out.println(s);
//        });
        beanProvider.stream().forEach(System.out::println);
    }

    //没有就创建一个
    private static void lookupByIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
//        User user = beanProvider.getIfAvailable(() -> User.createUser());
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println(user);

    }

    @Bean
    @Primary
    public String helloWorld(){
        return "Hello World";
    }

    @Bean
    public String messages(){
        return "messages";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
