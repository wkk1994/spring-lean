package com.wkk.learn.spring.ioc.overview.dependency.model;

/**
 * 简单用户类
 */
public class User {

    private String id;

    private String name;

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
                '}';
    }
}
