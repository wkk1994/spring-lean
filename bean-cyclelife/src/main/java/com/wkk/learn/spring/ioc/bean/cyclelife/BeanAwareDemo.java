package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description Bean Aware 接口回调示例
 * @Author Wangkunkun
 * @Date 2021/5/6 15:03
 */
public class BeanAwareDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("---------------");
        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //beanFactory.addBeanPostProcessor();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] localPath = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        reader.loadBeanDefinitions(localPath);
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("superUser", User.class);
        System.out.println(user2);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        MyBeanAware myBeanAware = beanFactory.getBean("myBeanAware", MyBeanAware.class);
        System.out.println(myBeanAware);
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        String[] localPath = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(localPath);
        applicationContext.refresh();
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = applicationContext.getBean("user", User.class);
        System.out.println(user1);
        User user2 = applicationContext.getBean("superUser", User.class);
        System.out.println(user2);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        MyBeanAware myBeanAware = applicationContext.getBean("myBeanAware", MyBeanAware.class);
        System.out.println(myBeanAware);
    }
}
