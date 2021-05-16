package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Description 基于Java注解 SpringIoC容器元信息配置示例
 * @Author Wangkunkun
 * @Date 2021/5/16 19:04
 */

@ImportResource("classpath:META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:META-INF/user-bean-definition.properties")
@PropertySource("classpath:META-INF/user-bean-definition.properties")
// 上面两个@PropertySource等价于
// @PropertySources({@PropertySource("classpath:META-INF/user-bean-definition.properties"), @PropertySource("classpath:META-INF/user-bean-definition.properties")})
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

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
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
            System.out.printf("bean Name: %s, bean Type: %s, content: %s %n", entry.getKey(), entry.getValue().getClass(), entry.getValue());
        }

        applicationContext.close();
    }
}
