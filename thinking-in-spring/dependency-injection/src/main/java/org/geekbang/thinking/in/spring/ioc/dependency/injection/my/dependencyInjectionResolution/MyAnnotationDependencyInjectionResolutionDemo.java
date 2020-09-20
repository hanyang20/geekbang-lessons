package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.dependencyInjectionResolution;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.my.filedinjection.MyAnnotationDependencyFiledInjectionDemo;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

@Configuration
public class MyAnnotationDependencyInjectionResolutionDemo {

    @Autowired
    public User user;//DependencyDescriptor 实时注入(eager=true) + 必须(required=true) + 通过类型(User.class) + 字段名称("user") + 是否首要(Primary=true)

    @Autowired          // 依赖查找（处理） + 延迟
    @Lazy
    private User lazyUser;

    @Autowired
    public Collection<User> userCollection;

    @Autowired          // 集合类型依赖注入
    private Map<String, User> users; // user superUser


    @Autowired
    private ObjectFactory<User> userObjectFactory; // superUser

    @MyAutowired
    private Optional<User> userOptional; // superUser

    @Inject
    private User inUser;

    @InjectedUser
    private User injectedUser;

    public MyAnnotationDependencyInjectionResolutionDemo() {
    }


//    @Bean(value = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    //会先加载静态方法,优先级高
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
////        Set<Class<? extends Annotation>> classes = new LinkedHashSet<>(Arrays.asList(InjectedUser.class,Autowired.class,MyAutowired.class));
////        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(classes);
//        return autowiredAnnotationBeanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public  static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();

        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyAnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.refresh();

        MyAnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(MyAnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user"+demo.user);
        System.out.println("demo.userCollection"+demo.userCollection);
        System.out.println("demo.users"+demo.users);
        System.out.println("demo.userObjectFactory"+demo.userObjectFactory);
        System.out.println("demo.userOptional"+demo.userOptional);
        System.out.println("demo.injectedUser"+demo.injectedUser);

        applicationContext.close();
    }
}
