package com.wkk.learn.spring.ioc.overview.dependency.model;

import com.wkk.learn.spring.ioc.overview.dependency.annotation.Super;

@Super
public class SuperUser extends User{

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "age=" + age +
                "} " + super.toString();
    }
}
