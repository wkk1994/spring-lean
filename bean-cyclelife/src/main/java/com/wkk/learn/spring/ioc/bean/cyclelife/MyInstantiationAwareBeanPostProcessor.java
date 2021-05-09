package com.wkk.learn.spring.ioc.bean.cyclelife;

/**
 * @Description 自定义Bean实例化、初始化阶段
 * @Author Wangkunkun
 * @Date 2021/5/9 09:36
 */

import com.wkk.learn.spring.ioc.overview.dependency.model.SuperUser;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            return new SuperUser();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            // "user" 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
            ((User) bean).setId("12345678");
            ((User) bean).setName("asdhjf;kas;lfaj");
            return false;
        }
        return true;
    }

    /**
     * Spring Bean属性赋值前阶段，将 {@link PropertyValues} 进行修改
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            System.out.println("postProcessProperties() --> The User Holder V2");
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(pvs);
            mutablePropertyValues.add("number", "123");
            if(mutablePropertyValues.contains("description")) {
                mutablePropertyValues.removePropertyValue("description");
                mutablePropertyValues.add("description", "The User Holder V2");
            }
            return mutablePropertyValues;
        }
        return null;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            System.out.println("postProcessBeforeInitialization() --> The User Holder V3");
            ((UserHolder) bean).setDescription("The User Holder V3");
            return bean;
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            System.out.println("postProcessAfterInitialization() --> The User Holder V7");
            ((UserHolder) bean).setDescription("The User Holder V7");
            return bean;
        }
        return null;
    }
}
