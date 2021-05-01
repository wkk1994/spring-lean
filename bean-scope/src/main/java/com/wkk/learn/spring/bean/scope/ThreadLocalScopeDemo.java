package com.wkk.learn.spring.bean.scope;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Description 自定义Scope {@link ThreadLocalScope} 代码示例
 * @Author Wangkunkun
 * @Date 2021/5/1 18:15
 */
public class ThreadLocalScopeDemo {

    @Autowired
    private User user1;

    @Autowired
    private User user2;

    @Bean
    @Scope(ThreadLocalScope.THREAD_LOCAL_SCOPE)
    private User threadLocalUser() {
        User user = new User();
        user.setId(String.valueOf(System.nanoTime()));
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.THREAD_LOCAL_SCOPE, new ThreadLocalScope());
        });
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    /**
     * Bean作用域之依赖查找
     *
     * @param applicationContext
     */
    private static void scopedBeansByLookup(ApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            User user = applicationContext.getBean("threadLocalUser", User.class);
            System.out.printf("当前线程id: %s, user: %s %n", Thread.currentThread().getId(), user);
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean("threadLocalUser", User.class);
                System.out.printf("当前线程id: %s, user: %s %n", Thread.currentThread().getId(), user);
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Bean作用域之依赖注入
     * 依赖注入都是一样的，原因是 {@link ThreadLocalScopeDemo} 已经固化
     * @param applicationContext
     */
    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            ThreadLocalScopeDemo bean = applicationContext.getBean(ThreadLocalScopeDemo.class);
            System.out.printf("当前线程id: %s, user1: %s %n", Thread.currentThread().getId(), bean.user1);
            System.out.printf("当前线程id: %s, user2: %s %n", Thread.currentThread().getId(), bean.user2);
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                ThreadLocalScopeDemo bean = applicationContext.getBean(ThreadLocalScopeDemo.class);
                System.out.printf("当前线程id: %s, user1: %s %n", Thread.currentThread().getId(), bean.user1);
                System.out.printf("当前线程id: %s, user2: %s %n", Thread.currentThread().getId(), bean.user2);
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
