package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyHierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);

        //获取beanFactory ConfigurableListableBeanFactory -> ConfigurableBeanFactory -> HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        HierarchicalBeanFactory parentBeanFactory =  getParentBeanFactory();
        //设置ParentBeanFactory
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 Parent BeanFactory ： " + beanFactory.getParentBeanFactory());
//
//        displayContainsLocalBean(beanFactory,"user");
//        displayContainsLocalBean(parentBeanFactory,"user");


        displayContainsBean(beanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");

        applicationContext.refresh();

        //关闭应用上下文
        applicationContext.close();
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory,beanName)){
                return true;
            }
        }

        return beanFactory.containsLocalBean(beanName);
    }
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("当前 beanfactory ["+ beanFactory + "], 是否包含"+beanName +"的实例: " + containsBean(beanFactory, beanName));
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("当前 beanfactory ["+ beanFactory + "], 是否包含"+beanName +"的实例: " + beanFactory.containsLocalBean(beanName));
    }
    private static ConfigurableBeanFactory getParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
