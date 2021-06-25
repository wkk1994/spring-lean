package com.wkk.learn.spring.ioc.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *  {@link Value @Value} 注解示例
 * @author Wangkunkun
 * @date 2021/6/25 16:13
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.refresh();
        ValueAnnotationDemo demo = applicationContext.getBean(ValueAnnotationDemo.class);
        System.out.println(demo.userName);
        applicationContext.close();
    }
}
