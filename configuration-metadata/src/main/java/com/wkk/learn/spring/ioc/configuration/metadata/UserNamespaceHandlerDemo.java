package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 使用Spring XML扩展机制，实现自定义user元素解析并注册示例
 * @Author Wangkunkun
 * @Date 2021/5/16 20:53
 */
public class UserNamespaceHandlerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/user-bean-definition.xml");

        User user = applicationContext.getBean(User.class);
        System.out.println(user);
    }
}
