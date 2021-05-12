package com.wkk.learn.spring.ioc.bean.cyclelife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Description Spring Bean销毁前回调
 * @Author Wangkunkun
 * @Date 2021/5/12 21:45
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {


    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            System.out.println("postProcessAfterInitialization() --> The User Holder V9");
            ((UserHolder) bean).setDescription("The User Holder V9");
        }
    }
}
