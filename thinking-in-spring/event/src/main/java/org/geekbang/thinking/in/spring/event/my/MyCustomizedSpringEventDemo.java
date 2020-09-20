package org.geekbang.thinking.in.spring.event.my;

import org.geekbang.thinking.in.spring.event.MySpringEvent;
import org.geekbang.thinking.in.spring.event.MySpringEvent2;
import org.geekbang.thinking.in.spring.event.MySpringEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class MyCustomizedSpringEventDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 1.添加自定义 Spring 事件监听器
        // ListenerRetriever -> 0 .. N 个 ApplicationListener<MySpringEvent> 实例
        // MySpringEvent 以及它子孙类
        context.addApplicationListener(new MySpringEventListener());

        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {

            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("Event : " + event);
            }
        });

        // 2.启动 Spring 应用上下文
        context.refresh();

        // 3. 发布自定义 Spring 事件
        // ListenerCacheKey -> MySpringEvent
        context.publishEvent(new MySpringEvent("Hello,World"));
        context.publishEvent(new MySpringEvent2("2020"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }
}
