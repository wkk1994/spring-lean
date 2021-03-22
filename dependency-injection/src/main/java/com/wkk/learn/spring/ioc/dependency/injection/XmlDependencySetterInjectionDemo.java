package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.UserHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description XML 实现 Setter依赖注入
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/xml-dependency-injection-context.xml");

        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
