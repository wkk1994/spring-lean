package com.wkk.learn.spring.ioc.bean.definition;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description Bean实例化方式示例
 * @Author Wangkunkun
 * @Date 2021/3/20 18:40
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");

        User userByStaticMethod = applicationContext.getBean("user-by-static-method", User.class);
        User userByInitMethod = applicationContext.getBean("user-by-init-method", User.class);
        User userByFactoryBean = applicationContext.getBean("user-by-factory-bean", User.class);
        System.out.println(userByStaticMethod);
        System.out.println(userByInitMethod);
        System.out.println(userByFactoryBean);
    }
}
