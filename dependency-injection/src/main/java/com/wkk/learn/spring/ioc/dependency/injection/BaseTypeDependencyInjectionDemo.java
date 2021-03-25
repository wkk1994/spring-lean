package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.BaseTypeEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 基础类型注入示例
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */
public class BaseTypeDependencyInjectionDemo {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/base-type-dependency-injection-context.xml");

        applicationContext.refresh();
        BaseTypeEntity baseTypeEntity = applicationContext.getBean(BaseTypeEntity.class);
        System.out.println(baseTypeEntity);
    }

}
