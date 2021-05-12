package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @Description Bean 初始化生命周期示例
 * @Author Wangkunkun
 * @Date 2021/5/6 10:20
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加Spring Bean销毁前回调 注册必须要先于CommonAnnotationBeanPostProcessor，否则输出的日志改动就不对了
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 添加CommonAnnotationBeanPostProcessor 支持@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] localPath = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        reader.loadBeanDefinitions(localPath);
        // 手动调用preInstantiateSingletons，用来触发SmartInitializingSingleton#afterSingletonsInstantiated的回调
        beanFactory.preInstantiateSingletons();
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("superUser", User.class);
        System.out.println(user2);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        // 手动销毁Bean实例
        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);
    }

}
