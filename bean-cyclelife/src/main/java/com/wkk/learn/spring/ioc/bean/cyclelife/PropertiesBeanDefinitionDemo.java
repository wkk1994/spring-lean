package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Description 使用Properties实现BeanDefinition的定义
 * @Author Wangkunkun
 * @Date 2021/5/2 21:25
 */
public class PropertiesBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

        String localPath = "MATE-INF/user.properties";
        Resource resource = new ClassPathResource(localPath);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        reader.loadBeanDefinitions(encodedResource);
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("user1", User.class);
        System.out.println(user2);


    }
}
