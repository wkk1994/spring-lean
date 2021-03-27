package com.wkk.learn.spring.ioc.bean.definition;

import com.wkk.learn.spring.ioc.bean.definition.factory.DefaultUserFactory;
import com.wkk.learn.spring.ioc.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 外部单体Bean注册实例 示例
 * @Author Wangkunkun
 * @Date 2021/3/20 23:30
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();

        UserFactory userFactoryBySingle = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println(userFactoryBySingle == userFactory);
        applicationContext.close();
    }
}
