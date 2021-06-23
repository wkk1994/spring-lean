package com.wkk.learn.spring.ioc.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld 模块 {@link ImportSelector} 实现
 * @author Wangkunkun
 * @date 2021/6/23 17:07
 * @see ImportSelector
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.wkk.learn.spring.ioc.annotation.HelloWorldConfiguration"};
    }
}
