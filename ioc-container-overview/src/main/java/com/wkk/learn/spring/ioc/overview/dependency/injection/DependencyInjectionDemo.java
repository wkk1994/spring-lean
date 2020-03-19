package com.wkk.learn.spring.ioc.overview.dependency.injection;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import com.wkk.learn.spring.ioc.overview.dependency.model.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入demo
 * @author wkk1994
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 1.根据Bean类型注入     依赖来源一：自定义Bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);

        System.out.println(userRepository);

        // 2.注入容器内建Bean对象（内建依赖） 依赖来源二：依赖注入（内建Bean）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // System.out.println(beanFactory.getBean(BeanFactory.class));  No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        // 依赖查找和依赖注入不同源

        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);
        //依赖来源三：容器内建Bean
        Environment bean = beanFactory.getBean(Environment.class);
        System.out.println(bean);
    }

}
