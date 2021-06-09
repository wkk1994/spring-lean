package com.wkk.learn.spring.ioc.overview.dependency.model;

/**
 * 公司信息
 * @author Wangkunkun
 * @date 2021/6/9 10:03
 */
public class Company {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
