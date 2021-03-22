package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.UserHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description XML 实现 Constructor依赖注入
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/xml-dependency-constructor-injection-context.xml");

        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
