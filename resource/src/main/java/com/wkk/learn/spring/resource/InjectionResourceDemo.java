package com.wkk.learn.spring.resource;

import com.wkk.learn.spring.resource.util.ResourceUtil;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @Description 依赖注入 {@link Resource} 对象示例
 * @Author Wangkunkun
 * @Date 2021/5/21 22:22
 * @see Resource
 * @see Value
 * @see AutowiredAnnotationBeanPostProcessor
 */

public class InjectionResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtil.getContext(resource));
        System.out.println("===============");
        Stream.of(resources).map(ResourceUtil::getContext).forEach(System.out::println);
        System.out.println("===============");
        System.out.println(userDir);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(InjectionResourceDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // 关闭 Spring 应用上下文
        context.close();
    }
}
