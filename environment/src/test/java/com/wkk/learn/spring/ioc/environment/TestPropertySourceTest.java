package com.wkk.learn.spring.ioc.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link TestPropertySource} 测试配置属性源示例
 * @author Wangkunkun
 * @date 2021/6/28 16:31
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)
@TestPropertySource(
        locations = "classpath:/META-INF/test.properties",
        properties = "user.name=你好"
)
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        Assert.assertEquals("你好", userName);
        for (PropertySource ps : environment.getPropertySources()) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }
}
