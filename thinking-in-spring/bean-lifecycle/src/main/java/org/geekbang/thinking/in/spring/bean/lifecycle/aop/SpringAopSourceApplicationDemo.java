package org.geekbang.thinking.in.spring.bean.lifecycle.aop;

import org.geekbang.thinking.in.spring.bean.lifecycle.aop.service.OrderService;
import org.geekbang.thinking.in.spring.bean.lifecycle.aop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopSourceApplicationDemo {

    public static void main(String[] args) {

        // 启动 Spring 的 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/aop.xml");

        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);

        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();

        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }
}
