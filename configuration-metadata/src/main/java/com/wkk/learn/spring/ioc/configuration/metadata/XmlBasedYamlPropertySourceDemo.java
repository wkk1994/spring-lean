package com.wkk.learn.spring.ioc.configuration.metadata;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Description 基于XML的Yaml资源作为外部化配置示例代码
 * @Author Wangkunkun
 * @Date 2021/5/18 21:59
 */
public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/yaml-property-source.xml");

        Map yamlMap = applicationContext.getBean("yamlMap", Map.class);
        System.out.println(yamlMap);
    }
}
