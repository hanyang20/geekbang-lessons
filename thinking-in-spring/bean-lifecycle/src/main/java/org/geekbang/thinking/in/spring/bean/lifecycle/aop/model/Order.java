package org.geekbang.thinking.in.spring.bean.lifecycle.aop.model;

public class Order {
    String username;
    String product;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
