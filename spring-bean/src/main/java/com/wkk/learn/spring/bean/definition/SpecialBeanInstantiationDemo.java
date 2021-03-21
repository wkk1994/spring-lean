package com.wkk.learn.spring.bean.definition;

import com.wkk.learn.spring.bean.definition.factory.DefaultUserFactory;
import com.wkk.learn.spring.bean.definition.factory.UserFactory;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description 特殊方式Bean实例化方式示例
 * @Author Wangkunkun
 * @Date 2021/3/20 22:02
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");
        // 方式1.通过ServiceLoader实例化bean
        ServiceLoader<UserFactory> serviceLoader = applicationContext.getBean("serviceLoader", ServiceLoader.class);
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().createUser());
        }
        // 方式2.通过AutowireCapableBeanFactory#createBean实例化Bean
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory bean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());
    }
}
