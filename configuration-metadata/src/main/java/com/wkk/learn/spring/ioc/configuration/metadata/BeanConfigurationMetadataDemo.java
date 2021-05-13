package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * @Description Spring Bean属性元信息示例代码
 * @Author Wangkunkun
 * @Date 2021/5/13 21:39
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "hello");
        beanDefinitionBuilder.addPropertyValue("id", "123");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 属性上下文设置
        beanDefinition.setAttribute("name", "你好");
        // 设置BeanDefinition的来源
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    if(BeanConfigurationMetadataDemo.class.equals(beanDefinition.getSource())) {
                        String name = (String) beanDefinition.getAttribute("name");
                        ((User) bean).setName(name);
                    }
                }
                return bean;
            }
        });

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
