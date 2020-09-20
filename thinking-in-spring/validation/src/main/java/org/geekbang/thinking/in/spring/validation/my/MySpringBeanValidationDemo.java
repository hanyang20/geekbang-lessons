package org.geekbang.thinking.in.spring.validation.my;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.util.Locale;

import static org.geekbang.thinking.in.spring.validation.my.MyErrorsMessageDemo.createMessageSource;

/**
 * Spring Bean Validation 整合示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Validator
 * @see LocalValidatorFactoryBean
 * @since
 */
public class MySpringBeanValidationDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");


        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        User user = new User();
        userProcessor.process(user);

        applicationContext.close();

    }

    @Component
    @Validated
    static class UserProcessor{
        public void process(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User {

        @NotNull(message = "name is not null")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
