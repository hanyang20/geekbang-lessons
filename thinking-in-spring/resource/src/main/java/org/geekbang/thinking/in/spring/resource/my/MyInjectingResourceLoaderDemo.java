package org.geekbang.thinking.in.spring.resource.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class MyInjectingResourceLoaderDemo implements ResourceLoaderAware{

    private ResourceLoader resourceLoader;//方法一

    @Autowired
    private ResourceLoader autowiredresourceLoader;//方法二

    @Autowired
    private ApplicationContext applicationContext;//方法三

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init(){
        System.out.println("resourceLoader == autowiredresourceLoader : " + (resourceLoader == autowiredresourceLoader));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("resourceLoader == applicationEventPublisher : " + (resourceLoader == applicationEventPublisher));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyInjectingResourceLoaderDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
