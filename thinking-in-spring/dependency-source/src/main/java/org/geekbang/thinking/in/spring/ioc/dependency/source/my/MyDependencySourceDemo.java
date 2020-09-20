package org.geekbang.thinking.in.spring.ioc.dependency.source.my;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class MyDependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init(){
        System.out.println("applicationContext == beanFactory: " + (beanFactory == applicationContext));
        System.out.println("applicationContext == AutowireCapableBeanFactory: " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("applicationContext == resourceLoader: " + (applicationContext == resourceLoader));
        System.out.println("applicationContext == applicationEventPublisher: " + (applicationContext == applicationEventPublisher));
    }

    @PostConstruct
    public void initLookUp(){
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);

    }

    public void getBean(Class clazz){
        try {
            System.out.println(beanFactory.getBean(clazz));
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("当前类型" + clazz.getName() + " 无法在 BeanFactory 中查找!");
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyDependencySourceDemo.class);

        applicationContext.refresh();

        MyDependencySourceDemo demo = applicationContext.getBean(MyDependencySourceDemo.class);

        applicationContext.close();
    }
}
