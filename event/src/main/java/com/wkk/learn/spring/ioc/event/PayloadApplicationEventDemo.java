package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 扩展 {@link PayloadApplicationEvent} 实现
 * @author Wangkunkun
 * @date 2021/6/20 10:57
 */
public class PayloadApplicationEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册事件监听
        applicationContext.addApplicationListener(event -> {
            System.out.println("接收到Spring事件：" + event);
        });
        applicationContext.addApplicationListener(new ApplicationListener<PayloadApplicationEvent>() {
            @Override
            public void onApplicationEvent(PayloadApplicationEvent event) {
                System.out.println("接收到Spring事件11：" + event.getSource());
            }
        });
        applicationContext.refresh();
        applicationContext.publishEvent(new MyPayloadApplicationEvent(applicationContext, "Hello,World"));
        applicationContext.publishEvent(1);
        applicationContext.close();
    }

    static class MyPayloadApplicationEvent<String> extends PayloadApplicationEvent<String> {


        public MyPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }
}
