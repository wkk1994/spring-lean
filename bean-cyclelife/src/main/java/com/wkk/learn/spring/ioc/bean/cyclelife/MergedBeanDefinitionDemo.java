package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Description BeanDefinition合并示例
 * @Author Wangkunkun
 * @Date 2021/5/3 18:19
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String localPath = "META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(localPath);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        reader.loadBeanDefinitions(encodedResource);
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("superUser", User.class);
        System.out.println(user2);
    }
}
