package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Description 通过 {@link PropertiesBeanDefinitionReader} 实现SpringBean属性元信息配置
 * @Author Wangkunkun
 * @Date 2021/5/14 17:34
 */
public class PropertiesBeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // Properties资源的加载默认使用ISO-8859-1，文档需要的是UTF-8
        String localPath = "META-INF/user-bean-definition.properties";
        Resource resource = new ClassPathResource(localPath);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
