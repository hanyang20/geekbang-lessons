package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionDefaults;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

// 3. 通过 @Import 来进行导入
@Import(value = MyAnnotationBeanDefinitionDemo.Config.class)
public class MyAnnotationBeanDefinitionDemo {


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(MyAnnotationBeanDefinitionDemo.class);

        // 通过 BeanDefinition 注册 API 实现
        //1.命名Bean方式
        registerUserBeanDefinition(applicationContext,"zhangsan-user");
        //2.非命Bean名方式
        registerUserBeanDefinition(applicationContext);

        //启动上下文
        applicationContext.refresh();
        //通过类型查找
        System.out.println("Config 类型的所有 Beans" + (applicationContext.getBeansOfType(Config.class)));
        System.out.println("User 类型的所有 Beans" + (applicationContext.getBeansOfType(User.class)));

        applicationContext.close();
    }


    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "张三");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry,null);
    }

    // 2. 通过 @Component 方式
    @Component // 定义当前类作为 Spring Bean（组件）
    public static class Config {
        // 1. 通过 @Bean 方式定义

        /**
         * 通过java注解的方式定义bean
         *
         * @return
         */
        @Bean
        public User user() {
            User user = new User();

            user.setId(1L);
            user.setName("张三");
            return user;
        }
    }
}
