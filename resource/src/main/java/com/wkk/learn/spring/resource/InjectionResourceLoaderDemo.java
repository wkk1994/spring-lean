package com.wkk.learn.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Description 依赖注入 {@link ResourceLoader} 对象示例
 * @Author Wangkunkun
 * @Date 2021/5/21 22:22
 * @see Resource
 * @see Value
 * @see AutowiredAnnotationBeanPostProcessor
 */

public class InjectionResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private AbstractApplicationContext abstractApplicationContext;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @PostConstruct
    public void init() {
        System.out.println("resourceLoader == autowiredResourceLoader : " + (this.resourceLoader == this.autowiredResourceLoader));
        System.out.println("resourceLoader == abstractApplicationContext : " + (this.resourceLoader == this.abstractApplicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(InjectionResourceLoaderDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
