package com.wkk.learn.spring.ioc.annotation;

import com.wkk.learn.spring.ioc.service.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * {@link ComponentScan}示例
 * @author Wangkunkun
 * @date 2021/6/22 16:36
 */

//@ComponentScan(basePackages = "com.wkk.learn.spring.ioc.annotation.service")
//@MyComponentScan(scanBasePackages = "com.wkk.learn.spring.ioc.service")
@MyComponentScan(basePackageClasses = Test.class)
//@MyComponentScan2(values = "com.wkk.learn.spring.ioc.annotation.service")
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(Test.class));
        applicationContext.close();
    }
}
