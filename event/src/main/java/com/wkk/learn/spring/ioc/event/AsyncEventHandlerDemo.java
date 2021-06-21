package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于接口API实现异步事件处理示例
 * @author Wangkunkun
 * @date 2021/6/21 12:29
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.refresh();

        applicationContext.addApplicationListener(new MySpringEventListener());

        ApplicationEventMulticaster applicationEventMulticaster = applicationContext.getBean(ApplicationEventMulticaster.class);
        if(applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            ((SimpleApplicationEventMulticaster) applicationEventMulticaster).setTaskExecutor(executorService);
            applicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if(!executorService.isShutdown()) {
                        executorService.shutdown();
                    }
                }
            });
            // 设置执行事件出现异常的处理方式
            ((SimpleApplicationEventMulticaster) applicationEventMulticaster).setErrorHandler(err -> {
                System.err.printf("[线程 ： %s] 当 Spring 事件异常时，原因：%s \n", Thread.currentThread().getName(), err.getMessage());
            });
        }
        applicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                throw new RuntimeException("抛出异常");
            }
        });
        applicationContext.publishEvent(new MySpringEvent("Hello, Event"));
        applicationContext.close();
    }
}
