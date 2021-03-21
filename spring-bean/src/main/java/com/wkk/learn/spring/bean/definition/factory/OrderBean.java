package com.wkk.learn.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description bean初始化/销毁测试
 * @Author Wangkunkun
 * @Date 2021/3/20 22:32
 */
public class OrderBean implements InitializingBean, DisposableBean {

    public OrderBean() {
        System.out.println("OrderBean ...");
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("init-PostConstruct 执行...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 执行...");
    }

    public void initBean() {
        System.out.println("init-Bean 执行...");
    }


    @PreDestroy
    public void destroyPreDestroy() {
        System.out.println("destroyPreDestroy 执行...");
    }

    public void destroyBean() {
        System.out.println("destroyBean 执行...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy 执行...");
    }
}
