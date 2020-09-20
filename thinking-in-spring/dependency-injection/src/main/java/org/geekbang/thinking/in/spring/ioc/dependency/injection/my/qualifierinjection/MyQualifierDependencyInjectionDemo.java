package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.qualifierinjection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.my.qualifierinjection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class MyQualifierDependencyInjectionDemo {


    @Autowired
    private User user; //superUser -> primary

//    @Autowired
//    @Qualifier("user") //指定 bean的名称或id
//    private User nameUser;
//
//
//    @Autowired
//    private Collection<User> allUsers;//1. 期待 user superUser user1 user2 //事实 user superUser 可以看QualifierGroupTest 加载全部可以看注解和xml spring 会优先加载xml文件里,在打印的时候,注解的可能还没注入到容器中,所以看不到所有的bean
//
//    @Autowired
//    @Qualifier
//    private Collection<User> qualifierUsers;// 2bean = user1 + user2
//                                            //加入UserGroup之后 没指定组 默认同一组 4bean = user1 + user2 + user3 + user4
                                            //可以用Qualifier进行扩展或逻辑上的分组 他只会查找带有Qualifier注解的对象


//    @Bean
//    @Qualifier
//    public User user1(){
//        return createUser(7L);
//    }
//
//    @Bean
//    @Qualifier
//    public User user2(){
//        return createUser(8L);
//    }
//
//    @Bean
//    @UserGroup
//    public User user3(){
//        return createUser(9L);
//    }
//
//    @Bean
//    @UserGroup
//    public User user4(){
//        return createUser(10L);
//    }

    public static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyQualifierDependencyInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resouce = "classpath:/META-INF/dependency-lookup-context.xml";

        xmlBeanDefinitionReader.loadBeanDefinitions(resouce);

        applicationContext.refresh();

        MyQualifierDependencyInjectionDemo bean = applicationContext.getBean(MyQualifierDependencyInjectionDemo.class);

        System.out.println("bean.user = " + bean.user);

//        System.out.println("bean.nameUser = " + bean.nameUser);
//        //期望输出 user superUser user1 user2->事实是只输出 user superUser
//        System.out.println("bean.allUsers = " + bean.allUsers);
//        //期望输出 user1 user2 符合预期
//        //加入UserGroup之后 输出 4gebean = user1 + user2 + user3 + user4
//        System.out.println("bean.qualifierUsers = " + bean.qualifierUsers);

        applicationContext.close();
    }
}
