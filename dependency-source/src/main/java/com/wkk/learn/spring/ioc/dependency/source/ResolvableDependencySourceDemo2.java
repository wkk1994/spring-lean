package com.wkk.learn.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Description ResolvableDependency作为依赖来源示例2
 * @Author Wangkunkun
 * @Date 2021/3/27 19:50
 */
public class ResolvableDependencySourceDemo2 {

    public ResolvableDependencySourceDemo2() {
        System.out.println("调用构造器...");
    }

    @Autowired
    private String value;

    @PostConstruct
    public void init () {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        if (applicationContext.getBeanFactory() instanceof ConfigurableListableBeanFactory) {
            applicationContext.getBeanFactory().registerResolvableDependency(String.class, "Hello ResolvableDependency");
        }
        applicationContext.refresh();
        applicationContext.register(ResolvableDependencySourceDemo2.class);
        // 必须要查找一次Bean才能触发Bean的初始化
        applicationContext.getBean(ResolvableDependencySourceDemo2.class);

        applicationContext.close();
    }
}
