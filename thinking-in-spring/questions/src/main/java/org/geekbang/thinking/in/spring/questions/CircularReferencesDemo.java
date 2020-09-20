/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geekbang.thinking.in.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * BeanFactory 循环引用（依赖）示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class CircularReferencesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(CircularReferencesDemo.class);

        // 如果设置为 false，则抛出异常信息如：currently in creation: Is there an unresolvable circular reference?
        // 默认值为 true
        context.setAllowCircularReferences(true);

        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println("Student : " + context.getBean(Student.class));
        System.out.println("ClassRoom : " + context.getBean(ClassRoom.class));
//        System.out.println("A : " + context.getBean(A.class));
//        System.out.println("B : " + context.getBean(B.class));
//        System.out.println("C : " + context.getBean(C.class));

        // 关闭 Spring 应用上下文
        context.close();
    }
//    @Bean
//    public static A a() {
//        A a = new A();
//        a.setName("A 的实例");
//        return a;
//    }
//    @Bean
//    public static B b() {
//        B b = new B();
//        b.setName("B 的实例");
//        return b;
//    }
//    @Bean
//    public static C c() {
//        C c = new C();
//        c.setName("C 的实例");
//        return c;
//    }

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        return student;
    }

    @Bean
    public static ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("C122");
        return classRoom;
    }
}
