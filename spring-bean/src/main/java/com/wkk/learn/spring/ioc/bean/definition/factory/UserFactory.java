package com.wkk.learn.spring.ioc.bean.definition.factory;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;

/**
 * @Description 创建 {@link com.wkk.learn.spring.ioc.overview.dependency.model.User} 的工厂类
 * @Author Wangkunkun
 * @Date 2021/3/20 18:44
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
