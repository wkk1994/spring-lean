package com.wkk.learn.spring.bean.definition;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description spring bean 别名使用示例
 * @Author Wangkunkun
 * @Date 2021/3/18 22:09
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");
        User user = (User) beanFactory.getBean("user");
        User aliasUser = (User) beanFactory.getBean("alias-user");
        System.out.println("user == aliasUser : " + (user == aliasUser));
    }
}
