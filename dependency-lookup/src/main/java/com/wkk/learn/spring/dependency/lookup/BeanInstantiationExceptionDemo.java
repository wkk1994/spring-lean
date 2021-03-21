package com.wkk.learn.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description {@link BeanInstantiationException} 错误示例
 * @Author Wangkunkun
 * @Date 2021/3/21 15:39
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectInterface.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    public interface ObjectInterface {

    }
}
