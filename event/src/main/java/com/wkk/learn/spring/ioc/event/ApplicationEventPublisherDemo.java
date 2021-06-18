package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link ApplicationEventPublisher} 示例
 * @author Wangkunkun
 * @date 2021/6/18 15:16
 */
public class ApplicationEventPublisherDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.registerBean(ApplicationEventPublisherDemo.class);
        applicationContext.addApplicationListener(event -> {
            System.out.println("接收到Spring事件：" + event);
        });

        applicationContext.refresh();
        applicationContext.start();
        // 关闭上下文
        applicationContext.close();
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello, Event") {
        });
        applicationEventPublisher.publishEvent("Hello, Event");
    }

}
