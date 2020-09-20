package org.geekbang.thinking.in.spring.validation.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

public class MyErrorsMessageDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setName("张三");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        errors.reject("user.properties.not.null");
        errors.rejectValue("name","user.name");
        errors.rejectValue("id","user.id");
        MessageSource messageSource = createMessageSource();

        for (ObjectError objectError : errors.getAllErrors()) {
            String message = messageSource.getMessage(objectError.getCode(), objectError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
        System.out.println("=======================");
        for (FieldError fieldError : errors.getFieldErrors()) {
            String message = messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
        System.out.println("=======================");

        for (ObjectError error : errors.getGlobalErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }


    }

     static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "user 所有属性不能为null");
        messageSource.addMessage("user.id", Locale.getDefault(), "id is not null");
        messageSource.addMessage("user.name", Locale.getDefault(), "name is not null");
        return messageSource;
    }
}
