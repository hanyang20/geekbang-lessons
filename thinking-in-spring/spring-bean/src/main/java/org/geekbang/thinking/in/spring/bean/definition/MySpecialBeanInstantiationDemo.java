package org.geekbang.thinking.in.spring.bean.definition;



import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MySpecialBeanInstantiationDemo {

        //通过 ServiceLoaderFactoryBean（配置元信息：XML、Java 注解和 Java API ）
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        //1.第一种 通过ServiceLoader获取bean
        ServiceLoader<UserFactory> userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        Iterator<UserFactory> iterator = userFactoryServiceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
        //2.第二种 通过ServiceLoader获取bean
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator1 = load.iterator();
        while (iterator1.hasNext()){
            UserFactory next = iterator1.next();
            System.out.println(next.createUser());
        }
    }
    //通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)
//    public static void main(String[] args) {
//        // 配置 XML 配置文件
//        // 启动 Spring 应用上下文
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
//        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
//        UserFactory bean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
//        User user = bean.createUser();
//        System.out.println(user);
//    }
}
