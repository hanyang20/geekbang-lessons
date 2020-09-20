package org.geekbang.thinking.in.spring.questions;

import org.springframework.beans.factory.annotation.Autowired;

public class C {

    private String name;

    @Autowired
    private A a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "C{" +
                "name='" + name + '\'' +
                ", a=" + a +
                '}';
    }
}
