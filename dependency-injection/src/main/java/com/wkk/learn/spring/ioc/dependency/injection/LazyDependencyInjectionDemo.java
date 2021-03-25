package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * @Description 使用 {@link ObjectProvider} 实现延迟注入
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */

public class LazyDependencyInjectionDemo {


    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Collection<User>> userObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("META-INF/xml-dependency-injection-context.xml");
        applicationContext.register(LazyDependencyInjectionDemo.class);
        applicationContext.refresh();

        LazyDependencyInjectionDemo bean = applicationContext.getBean(LazyDependencyInjectionDemo.class);

        System.out.println("demo.user: " + bean.user);
        System.out.println("demo.userObjectProvider: " + bean.userObjectProvider.getObject());
        System.out.println("demo.userObjectFactory: " + bean.userObjectFactory.getObject());
        // 使用 Qualifier实现分组
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.forEach(System.out::println);
    }

}
