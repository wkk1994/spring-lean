package com.wkk.learn.spring.ioc.environment;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * {@link PropertyPlaceholderConfigurer} 占位符处理示例
 * @author Wangkunkun
 * @date 2021/6/24 16:49
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-placeholder-resolver.xml");
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
