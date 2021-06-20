package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义Spring事件
 * @author Wangkunkun
 * @date 2021/6/20 12:09
 * @see ApplicationEvent
 */
public class MySpringEvent extends ApplicationEvent{

    public MySpringEvent(Object source) {
        super(source);
    }
}
