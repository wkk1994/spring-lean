package com.wkk.learn.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Description ResolvableDependency作为依赖来源示例1
 * @Author Wangkunkun
 * @Date 2021/3/27 19:50
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init () {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "Hello ResolvableDependency");
        });
        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }
}
