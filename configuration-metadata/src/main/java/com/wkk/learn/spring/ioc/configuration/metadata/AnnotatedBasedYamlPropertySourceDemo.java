package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.City;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description 基于Java注解的Yaml资源作为外部化配置示例代码
 * @Author Wangkunkun
 * @Date 2021/5/18 21:59
 */
@PropertySource(name = "yamlProperty", value = "classpath:META-INF/user.yaml",
    factory = YamlPropertySourceFactory.class)
public class AnnotatedBasedYamlPropertySourceDemo {

    /**
     * user.name 是 Java Properties 默认存在，当前用户：xujinxiu，而非配置文件中定义"小马哥"
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") String id, @Value("${user.name}") String name, @Value("${user.city}")City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedBasedYamlPropertySourceDemo.class);
        applicationContext.refresh();
        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
