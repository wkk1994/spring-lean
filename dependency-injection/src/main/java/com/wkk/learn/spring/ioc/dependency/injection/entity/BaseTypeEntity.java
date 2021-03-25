package com.wkk.learn.spring.ioc.dependency.injection.entity;

import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 基础类型注入示例实体
 * @Author Wangkunkun
 * @Date 2021/3/25 13:16
 */
public class BaseTypeEntity {


    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource localFilePath;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(Resource localFilePath) {
        this.localFilePath = localFilePath;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    @Override
    public String toString() {
        return "BaseTypeEntity{" +
                "city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", localFilePath=" + localFilePath +
                '}';
    }
}
