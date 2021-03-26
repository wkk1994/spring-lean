package com.wkk.learn.spring.ioc.dependency.injection.entity;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;

import java.lang.annotation.*;

/**
 * @Description 实现方式二：自定义注解注入 {@link User}
 * @Author Wangkunkun
 * @Date 2021/3/26 16:58
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
