package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.filedinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.my.constructorinjection.MyAnnotationDependencyConstructorInjectionDemo;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class MyAnnotationDependencyFiledInjectionDemo {

    @Autowired
    public  UserHolder userHolder;
    @Qualifier
    @Resource
    public  UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationDependencyFiledInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        MyAnnotationDependencyFiledInjectionDemo demo = applicationContext.getBean(MyAnnotationDependencyFiledInjectionDemo.class);
        UserHolder userHolder= demo.userHolder;
        UserHolder userHolder2 = demo.userHolder2;

        System.out.println(userHolder);

        System.out.println(userHolder == userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}
