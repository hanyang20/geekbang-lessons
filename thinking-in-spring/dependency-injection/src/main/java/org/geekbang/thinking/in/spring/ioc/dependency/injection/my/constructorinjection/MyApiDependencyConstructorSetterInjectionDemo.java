package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.constructorinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApiDependencyConstructorSetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinition beanDefinition = createUserHolderBeanDefinition();
        applicationContext.registerBeanDefinition("userHolder",beanDefinition);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);

        applicationContext.close();
    }

    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
//        beanDefinitionBuilder1.addPropertyReference("user","user");
        beanDefinitionBuilder1.addConstructorArgReference("superUser");
        return beanDefinitionBuilder1.getBeanDefinition();
    }
}
