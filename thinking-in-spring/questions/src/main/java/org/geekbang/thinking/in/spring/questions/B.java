package org.geekbang.thinking.in.spring.questions;

import org.springframework.beans.factory.annotation.Autowired;

public class B {

    private String name;

    @Autowired
    private C c;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", c=" + c +
                '}';
    }
}
