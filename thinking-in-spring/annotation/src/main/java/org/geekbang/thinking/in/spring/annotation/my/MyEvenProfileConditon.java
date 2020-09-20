package org.geekbang.thinking.in.spring.annotation.my;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;

public class MyEvenProfileConditon implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        for (MergedAnnotation<Annotation> annotationMergedAnnotation : metadata.getAnnotations()) {

            System.out.println(annotationMergedAnnotation);
        }
        ;
        return environment.acceptsProfiles("even");
    }
}
