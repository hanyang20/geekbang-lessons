package org.geekbang.thinking.in.spring.configuration.metadata.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 *
 * @since
 */
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class MyPropertySourceDemo {

    /**
     *     user.name 是 Java Properties 默认存在，当前用户：Administrator，而非配置文件中定义"小马哥" 通过外部化配置把配置文件置前
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

        //添加文件属性放置在第一位
        Map<String, Object> map = new HashMap<>();
        map.put("user.name","xiaomage");
        org.springframework.core.env.PropertySource<?> propertySource = new MapPropertySource("first-user-propertysource",map);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);

        //刷新 Spring 应用上下文
        applicationContext.refresh();

        // beanName 和 bean 映射
        Map<String, User> usersMap = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }
        System.out.println(applicationContext.getEnvironment().getPropertySources());
        //关闭 Spring 应用上下文
        applicationContext.close();
    }
}
