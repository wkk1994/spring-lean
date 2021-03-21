package com.wkk.learn.spring.bean.definition;

import com.wkk.learn.spring.bean.definition.factory.OrderBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @Description bean初始化方式示例
 * @Author Wangkunkun
 * @Date 2021/3/20 22:29
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println(".....");
        ObjectProvider<OrderBean> beanProvider = applicationContext.getBeanProvider(OrderBean.class);
        //OrderBean orderBean = applicationContext.getBean(OrderBean.class);
        System.out.println(".....");
        OrderBean object = beanProvider.getObject();
        //System.out.println(orderBean);
    }

    @Lazy
    @Bean(initMethod = "initBean")
    public OrderBean orderBean() {
        return new OrderBean();
    }
}
