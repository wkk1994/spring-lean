package com.wkk.learn.spring.bean.scope.web;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description Spring MVC配置类
 * @Author Wangkunkun
 * @Date 2021/5/1 14:16
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    //@Scope("request")
    //@RequestScope
    @SessionScope
    public User scopeUser() {
        User user = new User();
        user.setId(String.valueOf(System.nanoTime()));
        user.setName(String.valueOf(System.nanoTime()));
        return user;
    }

    @Bean
    @ApplicationScope
    public User user() {
        User user = new User();
        user.setId(String.valueOf(System.nanoTime()));
        user.setName("applicationScopeUser");
        return user;
    }
}
