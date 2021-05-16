package com.wkk.learn.spring.ioc.overview.dependency.model;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 简单用户类
 */
public class User implements BeanNameAware {

    private String id;

    private String name;

    private String beanBeam;

    private City city;

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", beanBeam='" + beanBeam + '\'' +
                ", city=" + city +
                '}';
    }

    @PostConstruct
    public void post() {
        System.out.println("当前beanName: "+beanBeam+"初始化...");
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
}
