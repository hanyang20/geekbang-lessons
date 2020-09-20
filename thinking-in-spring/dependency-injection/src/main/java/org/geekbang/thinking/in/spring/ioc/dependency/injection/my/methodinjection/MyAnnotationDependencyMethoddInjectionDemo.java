package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.methodinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class MyAnnotationDependencyMethoddInjectionDemo {

    public  UserHolder userHolder;

    public  UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationDependencyMethoddInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        MyAnnotationDependencyMethoddInjectionDemo demo = applicationContext.getBean(MyAnnotationDependencyMethoddInjectionDemo.class);
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
