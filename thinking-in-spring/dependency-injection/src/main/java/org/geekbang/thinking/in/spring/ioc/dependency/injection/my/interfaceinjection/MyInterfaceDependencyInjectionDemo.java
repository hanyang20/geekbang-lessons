package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.interfaceinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class MyInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware{


   public static BeanFactory beanFactory;

    public static ApplicationContext applicationContext;



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyInterfaceDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        context.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        MyInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
