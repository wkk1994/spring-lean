package com.wkk.learn.spring.ioc.overview.dependency.model;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author wkk1994
 */
public class UserRepository {

    private User user;

    private Collection<User> userCollection;

    private BeanFactory beanFactory;

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> objectFactory;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "user=" + user +
                ", userCollection=" + userCollection +
                ", beanFactory=" + beanFactory +
                '}';
    }
}
