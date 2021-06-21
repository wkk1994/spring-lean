package com.wkk.learn.spring.ioc.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * 基于注解实现异步事件处理示例
 * @author Wangkunkun
 * @date 2021/6/21 12:37
 */
@EnableAsync
public class AnnotationAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationAsyncEventHandlerDemo.class);
        applicationContext.refresh();
        applicationContext.publishEvent(new MySpringEvent("Hello,Event"));

        applicationContext.close();
    }

    @Async
    @EventListener
    public void listener(MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        return newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-a"));
    }

}
