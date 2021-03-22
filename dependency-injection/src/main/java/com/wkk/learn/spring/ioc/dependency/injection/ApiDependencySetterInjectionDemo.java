package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.UserHolder;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description Api 实现 Setter依赖注入
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addPropertyReference("user", "user");
        applicationContext.registerBeanDefinition("userHolder", beanDefinitionBuilder.getBeanDefinition());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("META-INF/annotation-dependency-injection-context.xml");

        applicationContext.refresh();

        UserHolder userHolder = (UserHolder) applicationContext.getBean("userHolder");
        System.out.println(userHolder);

        applicationContext.close();
    }

}
