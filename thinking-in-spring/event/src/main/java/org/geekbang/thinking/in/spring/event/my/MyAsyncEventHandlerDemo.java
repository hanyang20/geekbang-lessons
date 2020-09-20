package org.geekbang.thinking.in.spring.event.my;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 异步事件处理器示例  API
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 1.将引导类 ApplicationListenerDemo 作为 Configuration Class
        context.register(MySpringEventListener.class);
//        context.addApplicationListener(new MySpringEventListener());

        // 2.启动 Spring 应用上下文
        context.refresh(); // // 初始化 ApplicationEventMulticaster


        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;

            //设置异步线程池
//            ExecutorService taskExecutor = Executors.newSingleThreadExecutor();//只能默认名字pool-1-thread-1
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));//自定义名字
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

            simpleApplicationEventMulticaster.multicastEvent(new MySpringEvent("hello world"));

            //监听context.close() 关闭线程池
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!taskExecutor.isShutdown()) {
                        taskExecutor.shutdown();
                    }
                }
            });
            simpleApplicationEventMulticaster.setErrorHandler(e ->{
                System.err.println("spring 异常 原因 : " + e.getMessage());
            });
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
                @Override
                public void onApplicationEvent(ApplicationEvent event) {
                    throw new RuntimeException("故意抛出异常");
                }
            });
        }
        // 3.关闭 Spring 应用上下文
        context.close(); // ContextClosedEvent
    }
}
