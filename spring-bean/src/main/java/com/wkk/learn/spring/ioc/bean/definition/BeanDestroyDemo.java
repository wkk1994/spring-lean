package com.wkk.learn.spring.ioc.bean.definition;

import com.wkk.learn.spring.ioc.bean.definition.factory.OrderBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description bean初始化方式示例
 * @Author Wangkunkun
 * @Date 2021/3/20 22:29
 */
public class BeanDestroyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDestroyDemo.class);
        applicationContext.refresh();
        OrderBean orderBean = applicationContext.getBean(OrderBean.class);
        System.out.println(".....");
        System.out.println("Spring上下文开始关闭...");
        applicationContext.close();
        System.out.println("Spring上下文开始完成...");
    }

    @Bean(destroyMethod = "destroyBean")
    public OrderBean orderBean() {
        return new OrderBean();
    }
}
