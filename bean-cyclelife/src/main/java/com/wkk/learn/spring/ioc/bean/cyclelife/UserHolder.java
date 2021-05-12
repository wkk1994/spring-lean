package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/5/6 10:21
 */
public class UserHolder implements InitializingBean, SmartInitializingSingleton, DisposableBean {

    private User user;

    private Integer number;

    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct() --> The User Holder V4");
        this.description = "The User Holder V4";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() --> The User Holder V5");
        this.description = "The User Holder V5";
    }

    public void init() {
        System.out.println("init() --> The User Holder V6");
        this.description = "The User Holder V6";
    }


    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("afterPropertiesSet() --> The User Holder V8");
        this.description = "The User Holder V8";
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy() --> The User Holder V10");
        this.description = "The User Holder V10";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() --> The User Holder V11");
        this.description = "The User Holder V11";
    }

    public void disposable() {
        System.out.println("disposable() --> The User Holder V12");
        this.description = "The User Holder V12";
    }
}
