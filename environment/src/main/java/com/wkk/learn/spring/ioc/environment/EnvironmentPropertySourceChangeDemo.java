package com.wkk.learn.spring.ioc.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于API扩展Spring配置属性源示例
 * @author Wangkunkun
 * @date 2021/6/28 16:11
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentPropertySourceChangeDemo.class);

        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        // 在上下文刷新前更新PropertySource
        Map<String, Object> property = new HashMap<>();
        PropertySource propertySource = new MapPropertySource("customPropertySource", property);
        property.put("user.name", "hello");
        propertySources.addFirst(propertySource);

        applicationContext.refresh();

        // 在上下文刷新后更新PropertySource-不起作用
        property.put("user.name", "你好");

        EnvironmentPropertySourceChangeDemo bean = applicationContext.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(bean.userName);

        for (PropertySource<?> source : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", source.getName(), source.getProperty("user.name"));
        }
        applicationContext.close();
    }
}
