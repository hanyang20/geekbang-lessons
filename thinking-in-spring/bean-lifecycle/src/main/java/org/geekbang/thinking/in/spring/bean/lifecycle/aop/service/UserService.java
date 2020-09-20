package org.geekbang.thinking.in.spring.bean.lifecycle.aop.service;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public interface UserService {

    User createUser(String firstName, String lastName, int age);

    User queryUser();
}
