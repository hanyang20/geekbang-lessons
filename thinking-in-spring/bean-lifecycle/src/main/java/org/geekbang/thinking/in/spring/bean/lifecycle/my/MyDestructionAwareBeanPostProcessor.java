package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * {@link DestructionAwareBeanPostProcessor} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && org.geekbang.thinking.in.spring.bean.lifecycle.my.UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            //afterSingletonsInstantiated V8 -> postProcessBeforeDestruction V9
            System.out.println("postProcessBeforeDestruction()  The user holder v9");
            userHolder.setDescription("The user holder v9");
        }
    }
}
