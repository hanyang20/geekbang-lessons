package org.geekbang.thinking.in.spring.bean.lifecycle.aop.service;


import org.geekbang.thinking.in.spring.bean.lifecycle.aop.model.Order;

public interface OrderService {

    Order createOrder(String username, String product);

    Order queryOrder(String username);
}
