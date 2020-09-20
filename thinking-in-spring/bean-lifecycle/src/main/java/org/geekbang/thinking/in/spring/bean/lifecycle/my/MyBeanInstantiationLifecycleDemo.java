package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.bean.lifecycle.MyDestructionAwareBeanPostProcessor;
import org.geekbang.thinking.in.spring.bean.lifecycle.my.MyInstantiationAwareBeanPostProcessor;
import org.geekbang.thinking.in.spring.bean.lifecycle.UserHolder;
import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Bean 实例化生命周期示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyBeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("--------------------------");
        executeApplicationContext();

    }

    //普通的BeanFactory回调的只有三个BeanNameAware BeanClassLoaderAware BeanFactoryAware
   public static void executeBeanFactory(){
       DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
       // 方法一：添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
       //这段代码加不加都能回调setEnvironment
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
       // 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
       // 基于 XML 资源 BeanDefinitionReader 实现
       XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
       String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
       int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
       System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
       // 通过 Bean Id 和类型进行依赖查找
       // 构造器注入按照类型注入，resolveDependency
       org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder userHolder = beanFactory.getBean("userHolder", org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class);
       System.out.println(userHolder);
    }
    //Applicationconext回调的就有很多,因为它是一个内部类的关系,在Applicationconext在初始化的时候会动态的往我们的BeanFactory里面添加一个PostProcess
    public static void executeApplicationContext(){
        // 方法一：添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        // 构造器注入按照类型注入，resolveDependency
        org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder userHolder = beanFactory.getBean("userHolder", org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }
}
