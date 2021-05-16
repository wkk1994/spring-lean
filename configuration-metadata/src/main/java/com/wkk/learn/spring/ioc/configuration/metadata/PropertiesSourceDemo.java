package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 基于Properties资源装载外部化配置示例
 * @Author Wangkunkun
 * @Date 2021/5/16 21:47
 */
@PropertySource("classpath:META-INF/user-bean-definition.properties")
public class PropertiesSourceDemo {

    /**
     * user.name 是 Java Properties 默认存在，当前用户：xujinxiu，而非配置文件中定义"小马哥"
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") String id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PropertiesSourceDemo.class);
        // 扩展 Environment 中的 PropertySources
        // 添加 PropertySource 操作必须在 refresh 方法之前完成
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "firstName");
        org.springframework.core.env.PropertySource<?> propertySource = new MapPropertySource("first-property-source", source);
        applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
        applicationContext.refresh();
        //查看资源加载顺序
        System.out.println(applicationContext.getEnvironment());
        // 获取 User 对象
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
            System.out.printf("bean Name: %s, bean Type: %s, content: %s %n", entry.getKey(), entry.getValue().getClass(), entry.getValue());
        }

        applicationContext.close();

    }
}
