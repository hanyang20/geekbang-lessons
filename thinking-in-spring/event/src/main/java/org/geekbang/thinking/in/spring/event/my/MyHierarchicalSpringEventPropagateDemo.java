package org.geekbang.thinking.in.spring.event.my;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

public class MyHierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {

        //1.创建父上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyApplicationListener.class);
        //2.创建当前上文
        AnnotationConfigApplicationContext curentContext = new AnnotationConfigApplicationContext();
        curentContext.setId("curent-context");
        //3.设置父上下文
        curentContext.setParent(parentContext);
        curentContext.register(MyApplicationListener.class);

        //两个顺序不能相反,一定要父先refresh,后子refresh
        // 如果curentContext先refresh的话,parentContext没有调用refresh没有初始化initApplicationEventMulticaster,
        // 在后this.parent再次调用publishEvent的getApplicationEventMulticaster()时ApplicationEventMulticaster为空报错
        parentContext.refresh();
        curentContext.refresh();


        curentContext.close();
        parentContext.close();


    }

    static class MyApplicationListener implements ApplicationListener<ApplicationContextEvent> {

        private Set<ApplicationContextEvent> processedEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (processedEvents.add(event)){
                System.out.printf("监听到 Spring 应用上下文[ ID : %s ] 事件 :%s\n", event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }
}
