package com.wkk.learn.spring.ioc.overview.dependency.model;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * 简单用户类
 */
public class User implements BeanNameAware {

    private String id;

    private String name;

    private String beanBeam;

    private City city;

    private Company company;

    private Properties context;

    private Properties context1;

    private String contextAsText;

    public User() {
        System.out.println("456789");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User createUser() {
        User user = new User();
        user.setId("1");
        user.setName("hello");
        return user;
    }

    @PostConstruct
    public void post() {
        System.out.println("当前beanName: "+beanBeam+"初始化...");
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", beanBeam='" + beanBeam + '\'' +
                ", city=" + city +
                ", company=" + company +
                ", context=" + context +
                ", context1=" + context1 +
                ", contextAsText='" + contextAsText + '\'' +
                '}';
    }

    @PreDestroy
    public void destroy() {
        System.out.println("当前beanName: "+beanBeam+"销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanBeam = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public Properties getContext1() {
        return context1;
    }

    public void setContext1(Properties context1) {
        this.context1 = context1;
    }

    public String getContextAsText() {
        return contextAsText;
    }

    public void setContextAsText(String contextAsText) {
        this.contextAsText = contextAsText;
    }
}
