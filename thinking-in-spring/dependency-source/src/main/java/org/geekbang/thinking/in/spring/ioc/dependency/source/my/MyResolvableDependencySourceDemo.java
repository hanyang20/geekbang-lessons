package org.geekbang.thinking.in.spring.ioc.dependency.source.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class MyResolvableDependencySourceDemo {

    @Autowired
    String value;

    @PostConstruct
    public void initValue(){
        System.out.println(value);
    }


    //getAutowireCapableBeanFactory会检查是否处于激活状态, getBeanFactory是直接获取没有做任何检查
    //getAutowireCapableBeanFactory比getBeanFactory多了一个检查校验
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(MyResolvableDependencySourceDemo.class);
//
//
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//
//        beanFactory.registerResolvableDependency(String.class,"hello world");
//        applicationContext.refresh();
//        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
//
//        MyResolvableDependencySourceDemo demo = applicationContext.getBean(MyResolvableDependencySourceDemo.class);
//
//        applicationContext.close();
//    }

    //或者是通过addBeanFactoryPostProcessor
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(MyResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class,"hello world");
        });
        applicationContext.refresh();

        MyResolvableDependencySourceDemo demo = applicationContext.getBean(MyResolvableDependencySourceDemo.class);

        applicationContext.close();
    }
}
