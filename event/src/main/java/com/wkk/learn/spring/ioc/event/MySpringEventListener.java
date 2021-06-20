package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationListener;

/**
 * 自定义Spring事件监听器
 * @author Wangkunkun
 * @date 2021/6/20 12:10
 * @see ApplicationListener
 * @see MySpringEvent
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }
}
