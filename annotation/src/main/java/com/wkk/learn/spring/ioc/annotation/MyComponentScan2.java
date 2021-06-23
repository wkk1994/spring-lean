package com.wkk.learn.spring.ioc.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 传递性隐式别名示例
 * @author Wangkunkun
 * @date 2021/6/23 14:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "value")
    String[] values() default {"#"};

    // 和values为隐式别名
    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages")
    String[] scanBasePackages() default {"#"};
}
