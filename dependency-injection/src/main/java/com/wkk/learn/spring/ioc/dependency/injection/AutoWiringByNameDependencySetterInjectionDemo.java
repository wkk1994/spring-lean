package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.UserHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 自动模式 实现Setter注入
 * @Author Wangkunkun
 * @Date 2021/3/22 23:02
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/autowiring-dependency-injection-context.xml");

        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
