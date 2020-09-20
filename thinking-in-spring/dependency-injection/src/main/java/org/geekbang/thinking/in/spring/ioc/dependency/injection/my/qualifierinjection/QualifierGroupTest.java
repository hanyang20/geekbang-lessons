package org.geekbang.thinking.in.spring.ioc.dependency.injection.my.qualifierinjection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QualifierGroupTest {
    @Autowired
    private Collection<User> callerInfos;

    @Autowired
    @Qualifier("group1")
    private Set<User> group1Beans;

    @Autowired
    @Qualifier("group2")
    private List<User> group2Beans;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(QualifierGroupTest.class);
        configApplicationContext.refresh();

        QualifierGroupTest qualifierDemo = configApplicationContext.getBean(QualifierGroupTest.class);
        System.err.println("qualifierDemo.callerInfos" + qualifierDemo.callerInfos);
        System.err.println("qualifierDemo.group1Beans" + qualifierDemo.group1Beans);
        System.err.println("qualifierDemo.group2Beans" + qualifierDemo.group2Beans);
        configApplicationContext.close();
    }
    @Bean
    @Qualifier("group1")
    public User bean1() {
        return createCallerInfo("bean1");
    }

    @Bean
    @Qualifier("group1")
    public User bean2() {
        return createCallerInfo("bean2");
    }

    @Bean
    @Qualifier("group2")
    public User bean4() {
        return createCallerInfo("bean4");
    }

    @Bean
    @Qualifier("group2")
    public User bean6() {
        return createCallerInfo("bean6");
    }

    @Bean
    public User bean7() {
        return createCallerInfo("bean7");
    }

    @Bean
    @Qualifier
    public User bean10() {
        return createCallerInfo("bean10");
    }

    private static User createCallerInfo(String appName) {
        User callerInfo = new User();
        callerInfo.setName(appName);
        return callerInfo;
    }
}
