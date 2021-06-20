package com.wkk.learn.spring.ioc.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义Spring事件示例
 * @author Wangkunkun
 * @date 2021/6/20 12:12
 */
public class MySpringEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.addApplicationListener(new MySpringEventListener());
        applicationContext.refresh();
        applicationContext.publishEvent(new MySpringEvent("My Event"));
        applicationContext.stop();
    }
}
