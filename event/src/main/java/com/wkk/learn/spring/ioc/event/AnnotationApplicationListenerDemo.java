package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 基于注解实现Spring事件监听器
 * @author Wangkunkun
 * @date 2021/6/18 14:15
 * @see ApplicationListener
 */
 @EnableAsync
public class AnnotationApplicationListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.registerBean(AnnotationApplicationListenerDemo.class);
        applicationContext.register(MyApplicationListener.class);
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.stop();
        // 关闭上下文
        applicationContext.close();
    }

    @Async
    @EventListener
    public void contextRefreshedEvent(ContextRefreshedEvent event) {
        System.out.printf("线程name: %s， 监听到Spring事件：ContextRefreshedEvent\n", Thread.currentThread().getName());
    }

    @Order(2)
    @EventListener
    public void contextStartedEvent1(ContextStartedEvent event) {
        System.out.printf("线程name: %s， 监听到Spring事件：ContextStartedEvent1\n", Thread.currentThread().getName());
    }

    @Order(1)
    @EventListener
    public void contextStartedEvent2(ContextStartedEvent event) {
        System.out.printf("线程name: %s， 监听到Spring事件：ContextStartedEvent2\n", Thread.currentThread().getName());
    }

    @EventListener
    public void contextClosedEvent(ContextClosedEvent event) {
        System.out.printf("线程name: %s， 监听到Spring事件：ContextClosedEvent\n", Thread.currentThread().getName());
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        /**
         * Handle an application event.
         *
         * @param event the event to respond to
         */
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("MyApplicationListener : " + event);
        }
    }
}
