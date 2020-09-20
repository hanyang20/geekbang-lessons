package org.geekbang.thinking.in.spring.bean.lifecycle.aop.impl;

import org.geekbang.thinking.in.spring.bean.lifecycle.aop.service.UserService;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String firstName, String lastName, int age) {
        User user = new User();
        user.setName("哈哈");
        return user;
    }

    @Override
    public User queryUser() {
        User user = new User();
        user.setName("test");
        return user;
    }
}
