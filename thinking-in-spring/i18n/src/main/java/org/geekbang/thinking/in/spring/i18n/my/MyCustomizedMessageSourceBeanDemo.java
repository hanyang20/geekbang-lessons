package org.geekbang.thinking.in.spring.i18n.my;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static org.springframework.context.support.AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME;

@EnableAutoConfiguration
public class MyCustomizedMessageSourceBeanDemo {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        return messageSource;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(MyCustomizedMessageSourceBeanDemo.class).web(WebApplicationType.NONE).run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        if (beanFactory.containsBeanDefinition(MESSAGE_SOURCE_BEAN_NAME)) {
            // 查找 MessageSource 的 BeanDefinition
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
            // 查找 MessageSource Bean
            MessageSource bean = applicationContext.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(bean);
        }
        applicationContext.close();
    }
}
