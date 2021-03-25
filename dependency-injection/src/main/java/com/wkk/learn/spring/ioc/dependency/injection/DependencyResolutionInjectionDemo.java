package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * @Description 分析注解驱动的依赖注入处理过程
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */

public class DependencyResolutionInjectionDemo {


    @Autowired        // 依赖查找（处理过程）
    private User user;// DependencyDescriptor -->
                      // 实时注入（eager=true）
                      // 必须的（required=true）
                      // 通过类型（User.class）依赖查找
                      // 字段名称（user）
                      // 是否首要的（primary=true）

    @Autowired
    private Collection<User> users;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("META-INF/xml-dependency-injection-context.xml");
        applicationContext.register(DependencyResolutionInjectionDemo.class);
        applicationContext.refresh();

        DependencyResolutionInjectionDemo bean = applicationContext.getBean(DependencyResolutionInjectionDemo.class);

        System.out.println("demo.user: " + bean.user);
        System.out.println("demo.users: " + bean.users);
    }

}
