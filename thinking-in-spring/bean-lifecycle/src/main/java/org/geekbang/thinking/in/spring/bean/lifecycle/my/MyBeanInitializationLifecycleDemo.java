package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化生命周期示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyBeanInitializationLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        executeBeanFactory();
    }

    //普通的BeanFactory回调的只有三个BeanNameAware BeanClassLoaderAware BeanFactoryAware
    public static void executeBeanFactory() throws InterruptedException {
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

        //spring Bean完全初始化完成后,DefaultListableBeanFactory必须显示调用preInstantiateSingletons方法,而Applicationcontext会自动调用preInstantiateSingletons这个方法
        beanFactory.preInstantiateSingletons();
        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

//        User superUser = beanFactory.getBean("superUser", User.class);
//        System.out.println(superUser);
        // 构造器注入按照类型注入，resolveDependency
        org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder userHolder = beanFactory.getBean("userHolder", org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class);
        System.out.println(userHolder);


    }
}
