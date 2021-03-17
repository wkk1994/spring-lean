package com.wkk.learn.spring.ioc.overview.container;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @Description {@link org.springframework.context.ApplicationContext} 作为IoC容器使用示例
 * @Author Wangkunkun
 * @Date 2021/3/17 20:56
 */
//@Configuration //不加@Configuration ApplicationContextAsIoCContainerDemo不会被CGlib提升
public class ApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        configApplicationContext.register(ApplicationContextAsIoCContainerDemo.class);
        // 启动应用上下文
        configApplicationContext.refresh();
        // 依赖查找集合对象
        lookupCollectionsByType(configApplicationContext);
        ApplicationContextAsIoCContainerDemo bean = configApplicationContext.getBean(ApplicationContextAsIoCContainerDemo.class);
        System.out.println(bean);
        // 关闭上下文
        configApplicationContext.close();
    }

    /**
     * 根据类型查找集合对象
     * @param beanFactory
     */
    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> beansOfType = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("根据类型查找集合对象：" +beansOfType);
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId("1");
        user.setName("hello");
        return user;
    }

}
