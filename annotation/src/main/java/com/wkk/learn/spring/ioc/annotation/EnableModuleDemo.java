package com.wkk.learn.spring.ioc.annotation;

import com.wkk.learn.spring.ioc.service.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Wangkunkun
 * @date 2021/6/23 17:06
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("helloWorld", String.class));
        applicationContext.close();
    }
}
