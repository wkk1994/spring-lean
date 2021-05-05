package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.SuperUser;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * @Description {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInitialization(Object, String)} Bean实例化前阶段示例
 * @Author Wangkunkun
 * @Date 2021/5/4 22:46
 */
public class InstantiationAwareBeanPostProcessorDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
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

    /**
     * 自定义Bean实例化前阶段
     */
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }
    }
}
