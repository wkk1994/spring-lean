package com.wkk.learn.spring.ioc.java.beans;

/**
 * Setter / Getter
 * 可写方法（Writable）/可读方法（Readable）
 */
public class Person {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
