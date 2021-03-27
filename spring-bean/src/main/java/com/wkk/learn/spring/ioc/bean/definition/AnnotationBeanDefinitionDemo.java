package com.wkk.learn.spring.ioc.bean.definition;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Description 通过注解注册BeanDefinition的三种方式
 * @Author Wangkunkun
 * @Date 2021/3/18 22:23
 */
//方式三：通过@Import的方式
@Import(value = AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建容器
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AnnotationBeanDefinitionDemo.class);
        configApplicationContext.register(Config.class);
        configApplicationContext.refresh();
        // 获取bean
        System.out.println(configApplicationContext.getBeansOfType(AnnotationBeanDefinitionDemo.Config.class));
        System.out.println(configApplicationContext.getBeansOfType(User.class));
    }

    // 方式二：通过@Component注解的方式
    @Component
    public static class Config {

        //方式一：通过@Bean注解的方式
        @Bean(value = {"aliasUser","user"})
        public User user() {
            User user = new User();
            user.setId("1");
            user.setName("hello");
            return user;
        }
    }
}
