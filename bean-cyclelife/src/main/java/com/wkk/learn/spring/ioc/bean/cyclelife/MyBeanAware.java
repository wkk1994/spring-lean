package com.wkk.learn.spring.ioc.bean.cyclelife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @Description Bean Aware回调示例
 * @Author Wangkunkun
 * @Date 2021/5/6 15:01
 */
public class MyBeanAware implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InstantiationAwareBeanPostProcessor {

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public MyBeanAware() {
    }

    @Override
    public String toString() {
        return "MyBeanAware{" +
                "classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }
}
