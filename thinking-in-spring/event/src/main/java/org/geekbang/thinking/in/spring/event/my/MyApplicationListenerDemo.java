package org.geekbang.thinking.in.spring.event.my;

import org.geekbang.thinking.in.spring.event.ApplicationListenerDemo;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationListener
 * @see EventListener
 * @since
 */
@EnableAsync
public class MyApplicationListenerDemo implements ApplicationEventPublisherAware{

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 将引导类 ApplicationListenerDemo 作为 Configuration Class
        context.register(MyApplicationListenerDemo.class);

        // 方法一：基于 Spring 接口：向 Spring 应用上下文注册事件
        // a 方法：基于 ConfigurableApplicationContext API 实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("ApplicationListener - 接收到 Spring 事件：" + event);
            }
        });

        // b 方法：基于 ApplicationListener 注册为 Spring Bean
        // 通过 Configuration Class 来注册
        context.register(MyApplicationListener.class);

        // 方法二：基于 Spring 注解：@org.springframework.context.event.EventListener

        // ApplicationEventMulticaster
        // 启动 Spring 应用上下文
        context.refresh(); // ContextRefreshedEvent
        // 启动 Spring 上下文
        context.start();  // ContextStartedEvent
        // 停止 Spring 上下文
        context.stop();  // ContextStoppedEvent
        // 关闭 Spring 应用上下文
        context.close(); // ContextClosedEvent
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello world") {
        });
        applicationEventPublisher.publishEvent("hello world");
//        applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this, "hello world"));
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
                println("MyApplicationListener - 接收到 Spring 事件：" + event);
        }
    }

    //必须指定MyPayloadApplicationEvent的类型,不建议使用,直接使用默认ApplicationEventPublisher的publishEvent(Object event)的就行了
    static class MyPayloadApplicationEvent<String> extends PayloadApplicationEvent<String> {


        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent event){
        println("@EventListener - 接收到 Spring 事件：ContextRefreshedEvent ");
    }
    @EventListener
    @Order(2)
    public void onApplicationEvent1(ContextRefreshedEvent event){
        println("@EventListener - 接收到 Spring 事件：ContextRefreshedEvent1");
    }
    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event){
        println("@EventListener - @Async 接收到 Spring 事件：ContextRefreshedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event){
        println("@EventListener - 接收到 Spring 事件：ContextStartedEvent");
    }
    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event){
        println("@EventListener - 接收到 Spring 事件：ContextStoppedEvent");
    }
    @EventListener
    public void onApplicationEvent(ContextClosedEvent event){
        println("@EventListener - 接收到 Spring 事件：ContextClosedEvent");
    }

    private static void println(Object printable) {
        System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
    }
}
