package org.geekbang.thinking.in.spring.configuration.metadata.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public class MyBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        //BeanDefinition定义(生命)
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","mercity");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //附加属性 不影响bean的 populate、initialize
        beanDefinition.setAttribute("name","小马哥");
        //当前beanDefinition来自于何方 辅助作用
        beanDefinition.setSource(MyBeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Nullable
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition("user");
                    if (MyBeanConfigurationMetadataDemo.class.equals(bd.getSource())) { //通过source判断来源
                        //属性存储上下文
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }

                return bean;
            }
        });
        //注册beanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
