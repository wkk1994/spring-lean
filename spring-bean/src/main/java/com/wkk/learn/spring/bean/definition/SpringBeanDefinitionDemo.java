package com.wkk.learn.spring.bean.definition;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Description {@link org.springframework.beans.factory.config.BeanDefinition} 构建方式示例
 * 两种构建方式：
 * 1.通过{@link org.springframework.beans.factory.support.BeanDefinitionBuilder}
 * 2.通过{@link org.springframework.beans.factory.support.AbstractBeanDefinition}的派生类进行构建
 * @Author Wangkunkun
 * @Date 2021/3/18 21:03
 */
public class SpringBeanDefinitionDemo {

    public static void main(String[] args) {
        // 1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "1")
                .addPropertyValue("name", "hello bean definition1");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanDefinition并非最终状态，还可以进行修改

        // 2.通过AbstractBeanDefinition的派生类构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", "1").add("name", "hello bean definition2");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
