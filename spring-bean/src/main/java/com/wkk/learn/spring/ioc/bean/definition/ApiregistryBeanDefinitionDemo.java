package com.wkk.learn.spring.ioc.bean.definition;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 通过Java API 注册Bean
 * @Author Wangkunkun
 * @Date 2021/3/18 22:36
 */
public class ApiregistryBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        registryUserByHasName(configApplicationContext, "user");
        registryUserByHasName(configApplicationContext, "user");
        registryUserByOutName(configApplicationContext);
        configApplicationContext.refresh();
        // 获取bean
        System.out.println(configApplicationContext.getBeansOfType(User.class));
    }

    /**
     * 通过命名 API的方式进行注册
     * @param registry
     * @param beanName
     */
    public static void registryUserByHasName(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "1")
                .addPropertyValue("name", "hello");
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    /**
     * 通过非命名 API的方式进行注册
     * @param registry
     */
    public static void registryUserByOutName(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "1")
                .addPropertyValue("name", "hello");
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
    }
}
