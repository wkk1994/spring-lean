package com.wkk.learn.spring.ioc.bean.cyclelife;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @Description 注解BeanDefinition解析示例
 * @Author Wangkunkun
 * @Date 2021/5/2 22:14
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        // 注册当前类（非@Component注释的类）
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        System.out.println("已加载的bean数量：" + (beanDefinitionCountAfter - beanDefinitionCountBefore));

        AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(bean);
    }
}
