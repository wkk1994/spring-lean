package com.wkk.learn.spring.ioc.dependency.injection.entity;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;

/**
 * @Description UserHolder
 * @Author Wangkunkun
 * @Date 2021/3/22 22:50
 */
public class UserHolder {

    private User user;

    private User user1;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder(User user, User user1) {
        this.user = user;
        this.user1 = user1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", user1=" + user1 +
                '}';
    }
}

