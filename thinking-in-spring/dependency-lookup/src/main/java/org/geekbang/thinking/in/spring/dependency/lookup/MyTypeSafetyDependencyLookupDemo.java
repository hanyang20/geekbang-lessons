package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MyTypeSafetyDependencyLookupDemo {
@Autowired
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 MyTypeSafetyDependencyLookupDemo 作为配置类（Configuration Class）
        applicationContext.register(MyTypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        //演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderGetIfAvailable(applicationContext);

        //演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        //演示 ObjectProvider#stream 方法的安全性
        displayObjectProviderStream(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);

        printBeansException("displayObjectProviderStream", ()->beanProvider.stream().forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory applicationContext) {
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        printBeansException("displayListableBeanFactoryGetBeansOfTyp", ()->applicationContext.getBeansOfType(User.class));

    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);

        printBeansException("displayObjectProviderGetIfAvailable", ()->beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", ()->objectFactory.getObject());

    }

    public static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayBeanFactoryGetBean", ()->applicationContext.getBean(User.class));
    }
    public static void printBeansException(String source, Runnable runnable){
        System.err.println("======================================");
        System.err.println("source : " + source);
        try {
            runnable.run();
        }catch (BeansException ex){
            ex.printStackTrace();
        }
    }


}
