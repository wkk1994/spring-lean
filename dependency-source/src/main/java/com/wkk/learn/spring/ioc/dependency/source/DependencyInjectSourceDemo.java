package com.wkk.learn.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Description 依赖注入来源实例
 * @Author Wangkunkun
 * @Date 2021/3/27 10:36
 */
public class DependencyInjectSourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Autowired
    private AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor;

    @PostConstruct
    public void initByInject() {
        System.out.println("beanFactory == applicationContext : " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() : " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext : " + (applicationEventPublisher == applicationContext));
        System.out.println("environment: " + environment);
        System.out.println("AutowiredAnnotationBeanPostProcessor: " + autowiredAnnotationBeanPostProcessor);
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyInjectSourceDemo.class);
        applicationContext.refresh();
        DependencyInjectSourceDemo bean = applicationContext.getBean(DependencyInjectSourceDemo.class);
        bean.initByLookup();
        applicationContext.close();
    }
}
