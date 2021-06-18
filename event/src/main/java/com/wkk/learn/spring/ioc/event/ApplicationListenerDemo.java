package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 基于接口的 {@link ApplicationListener} 示例
 * @author Wangkunkun
 * @date 2021/6/18 13:53
 * @see ApplicationListener
 */
@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        // 注册事件监听
        applicationContext.addApplicationListener(event -> {
            System.out.println("接收到Spring事件：" + event);
        });
        //  启动Spring应用上下文
        applicationContext.refresh();
        //  启动Spring应用上下文
        applicationContext.start();
        // 关闭Spring应用上下文
        applicationContext.close();
    }
}
