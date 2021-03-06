package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Description Bean实例化阶段示例，传统实例化方式和构造器依赖注入实例化方式
 * @Author Wangkunkun
 * @Date 2021/5/6 10:20
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] localPath = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        reader.loadBeanDefinitions(localPath);
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("superUser", User.class);
        System.out.println(user2);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

}
