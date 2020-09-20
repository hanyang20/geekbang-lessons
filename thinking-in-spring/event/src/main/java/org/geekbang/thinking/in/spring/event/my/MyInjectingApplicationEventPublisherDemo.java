package org.geekbang.thinking.in.spring.event.my;

import org.geekbang.thinking.in.spring.event.InjectingApplicationEventPublisherDemo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class MyInjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        applicationEventPublisher.publishEvent(new MySpringEvent("@Autowired applicationEventPublisher 3"));
        applicationContext.publishEvent(new MySpringEvent("@Autowired applicationContext 4"));
    }

    public static void main(String[] args) {
        // 创建注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class
        context.register(MyInjectingApplicationEventPublisherDemo.class);

        // 增加 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());

        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringEvent("ApplicationEventPublisherAware 1"));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new MySpringEvent("ApplicationContextAware 2"));
    }
}
