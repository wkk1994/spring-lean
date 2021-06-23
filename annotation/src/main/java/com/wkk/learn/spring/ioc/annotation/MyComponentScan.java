package com.wkk.learn.spring.ioc.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 隐式别名示例
 * @author Wangkunkun
 * @date 2021/6/23 14:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface MyComponentScan {

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] values() default {"#"};

    // 和values为隐式别名
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {"#"};

}
