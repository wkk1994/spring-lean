package com.wkk.learn.spring.dependency.lookup;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Description {@link org.springframework.beans.factory.ObjectProvider} 使用示例
 * @Author Wangkunkun
 * @Date 2021/3/21 10:56
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());

        // 延迟依赖查找的方式
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象：" + user);

        // 延迟依赖查找bean集合
        ObjectProvider<String> beanProviderList = applicationContext.getBeanProvider(String.class);
        beanProviderList.stream().forEach(System.out::println);
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    public String helloWorld1() {
        return "Hello World1";
    }
}
