package com.wkk.learn.spring.ioc.overview.dependency.lookup;

import com.wkk.learn.spring.ioc.overview.dependency.annotation.Super;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 依赖查找示例
 * 1，通过名称查找
 * 2，通过类型查找
 * 3，通过注解查找
 * @author wkk1994
 */
public class DependencyLookupDemo {


    public static void main(String[] args) throws BeansException {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        lookupInReadTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupByNameAndType(beanFactory);
        lookupCollectionsByType(beanFactory);
        lookupCollectionByAnnotationType(beanFactory);

    }

    /**
     * 根据名称实时查找
     * @param beanFactory
     */
    private static void lookupInReadTime(BeanFactory beanFactory) throws BeansException {
        User user = (User) beanFactory.getBean("user");
        System.out.println("根据名称实时查找" + user);
    }

    /**
     * 根据名称延时查找
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("根据名称延时查找" + user);
    }

    /**
     * 根据类型查找
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找 实时查找：" + user);
    }

    /**
     * 根据名称+类型查找
     * @param beanFactory
     */
    private static void lookupByNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("根据名称+类型查询：" + user);
    }

    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> beansOfType = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("根据类型查找集合对象：" +beansOfType);
        }
    }

    /**
     * 根据注解查找
     * @param beanFactory
     */
    private static void lookupCollectionByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, Object> beansOfType = ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class);
            System.out.println("根据注解查找集合对象：" +beansOfType);
        }

    }
}
