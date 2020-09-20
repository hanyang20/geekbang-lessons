package org.geekbang.thinking.in.spring.ioc.bean.scope;

import com.sun.org.apache.regexp.internal.RE;
import org.geekbang.thinking.in.spring.ioc.overview.container.MyAnnotationApplicationContextAsIoCContainerDemo;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;

import java.util.Map;

public class MyBeanScopeDemo implements DisposableBean{

    @Bean
    public static User singletonUser(){
         return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> map;

    @Autowired
    private static ConfigurableListableBeanFactory beanFactory;

    //结论一 : 依赖注入 singleton只会注入一次, prototype每次都会创建新的对象
    //        依赖查找 singleton只会查找同一个, prototype每次都是新的对象

    //结论二 : 注入集合对象 Singleton Bean和Prototype Bean均会只存在一个
    // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

    //结论二 : 无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调, 仅Singleton Bean才会执行销毁方法



    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyBeanScopeDemo.class);

        //不推荐这种初始化bean后的回调
        applicationContext.addBeanFactoryPostProcessor(beanFactory1 -> {
            beanFactory1.addBeanPostProcessor(new BeanPostProcessor() {
                @Nullable
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        MyBeanScopeDemo bean = applicationContext.getBean(MyBeanScopeDemo.class);
        System.out.println("bean.singletonUser = "+bean.singletonUser);
        System.out.println("bean.singletonUser2 = "+bean.singletonUser2);
        System.out.println("bean.prototypeUser = "+bean.prototypeUser);
        System.out.println("bean.prototypeUser2 = "+bean.prototypeUser2);

        System.out.println("bean.map = "+bean.map);


    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0;i < 3 ;i++){
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = "+singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser = "+prototypeUser);

        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        singletonUser.destroy();
        singletonUser2.destroy();
        prototypeUser.destroy();
        prototypeUser2.destroy();

        for (Map.Entry<String, User> entry : map.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            if (beanDefinition.isPrototype()){// 如果当前 Bean 是 prototype scope
                entry.getValue().destroy();
            }
        }

        System.out.println("当前 BeanScopeDemo Bean 销毁完成...");
    }
}
