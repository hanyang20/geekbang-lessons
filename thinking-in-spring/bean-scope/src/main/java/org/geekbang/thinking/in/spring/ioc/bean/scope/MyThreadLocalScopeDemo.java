package org.geekbang.thinking.in.spring.ioc.bean.scope;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class MyThreadLocalScopeDemo {

    @Bean
    @Scope(MyThreadLocalScope.SCOPE_NAME)
    public static User user() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(MyThreadLocalScope.SCOPE_NAME,new MyThreadLocalScope());
        });

        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                // user 是共享 Bean 对象
                User user = applicationContext.getBean("user", User.class);
                System.out.println("user = " + user);
            });
            thread.start();

            //强制执行
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
