package com.wkk.learn.spring.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性Spring事件传播示例
 * @author Wangkunkun
 * @date 2021/6/18 15:53
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 1.创建parent Spring应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyApplicationListener.class);
        // 2.创建 current Spring应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyApplicationListener.class);
        // 3.currentContext -> parent
        currentContext.setParent(parentContext);

        // 启动parent、current Spring上下文
        parentContext.refresh();
        currentContext.refresh();
        // 关闭parent、current Spring上下文
        // parent要在current之后关闭，否则parent的关闭事件只能触发一次，因为current关闭上下文时，parent上下文已经关闭了，事件不会触发
        currentContext.close();
        parentContext.close();
    }

    static class MyApplicationListener implements ApplicationListener<ApplicationContextEvent> {

        private static Set<ApplicationContextEvent> processesEvent = new LinkedHashSet<>();
        /**
         * Handle an application event.
         *
         * @param event the event to respond to
         */
        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            // 去除重复的Spring事件 TODO 这样只是简单处理，有内存溢出问题
            if(processesEvent.add(event)) {
                System.out.printf("线程name : %s，监听到 Spring 应用上下文[id: %s] 的%s\n", Thread.currentThread().getName(),
                        event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }
}
