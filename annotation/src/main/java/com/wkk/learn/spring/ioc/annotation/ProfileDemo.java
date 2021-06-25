package com.wkk.learn.spring.ioc.annotation;

import com.wkk.learn.spring.ioc.service.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Spring条件注解示例
 * @author Wangkunkun
 * @date 2021/6/23 17:32
 * @see org.springframework.context.annotation.Profile
 */
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProfileDemo.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // 设置默认环境
        environment.setDefaultProfiles("odd");
        // 设置活跃环境
        environment.setActiveProfiles("even");
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("number", Integer.class));
        applicationContext.close();
    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
    //@Profile("even")
    @Conditional(EvenProfileCondition.class)
    public Integer even() {
        return 2;
    }
}
