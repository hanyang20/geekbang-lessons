package org.geekbang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        HelloService service = (msg) ->{
            System.out.println(msg);
            return msg;
        };
        System.out.println(service.say("mic"));

        //中间操作
        //filter 过滤
        //sorted 排序
        //disticnt 去重

        //map映射

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("张三");
        user.setAge(10);
        User user1 = new User();
        user1.setName("李四");
        user1.setAge(18);

        users.add(user);
        users.add(user);
        users.add(user1);

        List<String> collect = users.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect1 = users.stream().map(User::getAge).collect(Collectors.toList());

        System.out.println(collect1);

        List<User> collect2 = users.stream().distinct().collect(Collectors.toList());
        System.out.println(collect2);

        List<User> collect3 = users.stream().filter(i->i.getName().equals("李四")).collect(Collectors.toList());
        System.out.println(collect3);

    }
}
