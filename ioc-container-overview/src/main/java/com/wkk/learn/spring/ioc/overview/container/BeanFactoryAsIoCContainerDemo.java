package com.wkk.learn.spring.ioc.overview.container;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Description {@link BeanFactory} 作为IoC容器使用示例
 * @Author Wangkunkun
 * @Date 2021/3/17 20:56
 */
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 读取配置文件
        int definitions = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        System.out.println("bean加载的数量： " + definitions);
        // 依赖查找集合对象
        lookupCollectionsByType(beanFactory);
    }

    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> beansOfType = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("根据类型查找集合对象：" +beansOfType);
        }
    }

}
