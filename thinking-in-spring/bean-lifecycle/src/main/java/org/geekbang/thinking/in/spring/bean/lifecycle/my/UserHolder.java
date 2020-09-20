package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private User user;

    private Integer number;

    private String description;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;
    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder() {
    }

    //注解驱动
    @PostConstruct
    public void PostConstruct(){
        //postProcessBeforeInitialization V3 -> PostConstruct V4
        System.out.println("PostConstruct() The user holder v4");
        this.description = "The user holder v4";
    }

    @Override
    public void afterPropertiesSet() throws Exception{
    //PostConstruct V4 ->  afterPropertiesSet v5
        System.out.println("afterPropertiesSet() The user holder v5");
        this.description = "The user holder v5";
    }

    //自定义初始化方法
    public void init() throws Exception{
        //PostConstruct V5 ->  afterPropertiesSet v6
        System.out.println("init() The user holder v6");
        this.description = "The user holder v6";
    }



    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware 的回调");
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("EnvironmentAware 的回调");
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }

    @Override
    public void afterSingletonsInstantiated() {
        //postProcessAfterInitialization V7 -> afterSingletonsInstantiated V8
        System.out.println("afterSingletonsInstantiated() The user holder v8");
        this.description = "The user holder v8";
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        System.out.println("@PreDestroy The user holder v10");
        this.description = "The user holder v10";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() The user holder v11");
        this.description = "The user holder v11";
    }

    public void doDestroy() throws Exception {
        System.out.println("doDestroy() The user holder v12");
        this.description = "The user holder v12";
    }
}
