package org.geekbang.thinking.in.spring.validation.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static org.geekbang.thinking.in.spring.validation.my.MyErrorsMessageDemo.createMessageSource;

/**
 * 自定义 Spring {@link Validator} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Validator
 * @since
 */
public class MyValidatorDemo {

    public static void main(String[] args) {
        //1.创建Validator
        Validator validator = new UserValidator();
        User user = new User();
        //2.检验是否支持user
        System.out.println("是否支持 User: "+(validator.supports(user.getClass())));

        // 3.创建messageSource
        MessageSource messageSource = createMessageSource();
        //4.创建Errors
        Errors errors = new BeanPropertyBindingResult(user, "user");
        //执行validate
        validator.validate(user, errors);
        //5.打印错误文案
        for (ObjectError objectError : errors.getAllErrors()) {
            String message = messageSource.getMessage(objectError.getCode(), objectError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static  class UserValidator implements Validator{

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "user.id");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "user.name");
            String userName = user.getName();
            // ...

        }
    }
}
