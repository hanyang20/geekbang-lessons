package org.geekbang.thinking.in.spring.event.my;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 异步事件处理器示例 注解
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@EnableAsync// 激活 Spring 异步特性
public class MyAnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 将引导类 MyAnnotatedAsyncEventHandlerDemo 作为 Configuration Class
        context.register(MyAnnotatedAsyncEventHandlerDemo.class);

        // ApplicationEventMulticaster
        // 启动 Spring 应用上下文
        context.refresh(); // ContextRefreshedEvent
        context.publishEvent(new MySpringEvent("hello world"));
        // 关闭 Spring 应用上下文
        context.close(); // ContextClosedEvent
    }

    @EventListener
    @Async
    public void onEvent(MySpringEvent event){
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
        return executorService;
    }
}
