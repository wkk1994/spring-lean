package com.wkk.learn.spring.bean.definition.factory;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/3/20 18:49
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId("1");
        user.setName("hello factory bean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

}
