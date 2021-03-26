package com.wkk.learn.spring.ioc.dependency.injection.entity;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * @Description 实现方式一：通过 {@link Autowired} 实现自定义注解注入 {@link User}
 * @Author Wangkunkun
 * @Date 2021/3/26 16:58
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface AutowiredUser {
}
