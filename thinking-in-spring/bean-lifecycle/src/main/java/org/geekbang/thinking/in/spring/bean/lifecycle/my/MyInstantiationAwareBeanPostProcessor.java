package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Nullable
    @Override
    //初始化前阶段
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            System.out.println("postProcessBeforeInitialization()  The user holder V3");
            userHolder.setDescription("The user holder v3");
        }
        return bean;
    }

    @Nullable
    @Override
    //初始化后阶段
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class.equals(bean.getClass())) {
            org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder userHolder = (org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder) bean;
            // init() = The user holder V6 - postProcessAfterInitialization V7
            // UserHolder description = "The user holder V6"
            System.out.println("postProcessAfterInitialization()  The user holder V7");
            userHolder.setDescription("The user holder V7");
        }
        return bean;
    }

    @Nullable
    @Override
    //实例化前阶段的回调
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(beanClass)) {
            //覆盖spring的实例化操作 Bean覆盖
            System.out.println("postProcessBeforeInstantiation() 实例化前阶段的回调");
            return new User();
        }
        return null;
    }

    @Override
    //实例化后阶段的回调
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            User user = (User) bean;
            user.setId(2L);
            user.setName("mercyblitz");
            System.out.println("postProcessAfterInstantiation() 实例化后阶段的回调");

            // "user" 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
            return false;
        }
        return true;
    }

    // user 是跳过 Bean 属性赋值（填入）
    // superUser 也是完全跳过 Bean 实例化（Bean 属性赋值（填入））
    // userHolder
    @Nullable
    @Override
    //属性赋值前的回调
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class.equals(bean.getClass())) {
            MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            propertyValues.add("number", "2");
            if (propertyValues.contains("description")) {
                propertyValues.removePropertyValue("description");
                System.out.println("postProcessProperties()  The user holder v2");

                propertyValues.addPropertyValue("description", "The user holder v2");
            }
            return propertyValues;
        }

        return null;
    }
}
