package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/5/6 10:21
 */
public class UserHolder implements InitializingBean {

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
}
