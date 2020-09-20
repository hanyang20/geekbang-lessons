package org.geekbang.thinking.in.spring.annotation.my;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class MyProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(MyProfileDemo.class);

        //获取environment对象
        ConfigurableEnvironment environment = context.getEnvironment();
        //设置默认Profiles
        environment.setDefaultProfiles("odd");
        environment.setActiveProfiles("even");

        context.refresh();

        Integer even = context.getBean("number", Integer.class);
        System.out.println(even);

        context.close();
    }

    @Bean("number")
    @Profile("odd")//奇数
    public Integer odd(){
        return 1;
    }
//    @Bean
    //第一种定义 @Profile("even")
//    @Profile("even")
//    public Integer even(){
//        return 2;
//    }

    //第二种实现Conditon
    @Bean("number")//偶数
//    @Profile("even")
    @Conditional(MyEvenProfileConditon.class)
    public Integer even(){
        return 2;
    }



}


