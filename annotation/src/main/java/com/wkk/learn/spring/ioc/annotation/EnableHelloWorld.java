package com.wkk.learn.spring.ioc.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活"HelloWorld"模块注解
 * @author Wangkunkun
 * @date 2021/6/23 17:03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 方式一：通过 Configuration Class 实现
//@Import(HelloWorldConfiguration.class)
// 方式二：通过ImportSelector接口实现
//@Import(HelloWorldImportSelector.class)
//方式三：通过ImportBeanDefinitionRegistrar接口实现
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
