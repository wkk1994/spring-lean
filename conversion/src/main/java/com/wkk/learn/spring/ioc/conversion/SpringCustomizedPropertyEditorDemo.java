package com.wkk.learn.spring.ioc.conversion;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现
 * @author Wangkunkun
 * @date 2021/6/10 15:23
 * @see CustomizedPropertyEditorRegistrar
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/property-editors-context.xml");
        User user = applicationContext.getBean(User.class);
        System.out.println(user);

    }
}
