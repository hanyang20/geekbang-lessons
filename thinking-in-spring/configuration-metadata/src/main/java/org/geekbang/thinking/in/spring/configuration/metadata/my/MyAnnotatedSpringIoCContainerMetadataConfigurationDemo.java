package org.geekbang.thinking.in.spring.configuration.metadata.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)

@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
// @PropertySources(@PropertySource(...))
public class MyAnnotatedSpringIoCContainerMetadataConfigurationDemo {
    /**
     *     user.name 是 Java Properties 默认存在，当前用户：Administrator，而非配置文件中定义"小马哥"
     * @param id
     * @param name
     * @return
     */
    @Bean
    private User configUser(@Value("${user.id}") Long id, @Value("${user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类注册为 Configration class
        applicationContext.register(MyAnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        //刷新 Spring 应用上下文
        applicationContext.refresh();

        for (User user : applicationContext.getBeanProvider(User.class)) {
            System.out.println(user);
        }

        //关闭 Spring 应用上下文
        applicationContext.close();
    }
}
