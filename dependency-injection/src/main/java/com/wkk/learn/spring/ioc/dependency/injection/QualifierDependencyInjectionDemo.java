package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.UserGroup;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @Description 使用 {@link Qualifier} 实现限定注入
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */

public class QualifierDependencyInjectionDemo {


    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User user1;

    @Autowired
    private Collection<User> allUsers; // 2 Beans = user + superUser 如果将xml配置的bean拿到当前类定义就会有4个bean

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; // 2 Beans = user1 + user2

    @Autowired
    @UserGroup
    private Collection<User> groupUsers; // 2 Beans = user3 + user4

    @Bean
    @Qualifier
    public User user1() {
        User user = new User();
        user.setId("1");
        user.setName("user1");
        return user;
    }

    @Bean
    @Qualifier
    public User user2() {
        User user = new User();
        user.setId("2");
        user.setName("user2");
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setId("3");
        user.setName("user4");
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setId("4");
        user.setName("user4");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("META-INF/xml-dependency-injection-context.xml");
        applicationContext.register(QualifierDependencyInjectionDemo.class);
        applicationContext.refresh();

        QualifierDependencyInjectionDemo bean = applicationContext.getBean(QualifierDependencyInjectionDemo.class);

        System.out.println("demo.user: " + bean.user);
        System.out.println("demo.user1: " + bean.user1);
        // 使用 Qualifier实现分组
        System.out.println("demo.allUsers: " + bean.allUsers);
        System.out.println("demo.qualifierUsers: " + bean.qualifierUsers);
        System.out.println("demo.groupUsers: " + bean.groupUsers);
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.forEach(System.out::println);
    }

}
