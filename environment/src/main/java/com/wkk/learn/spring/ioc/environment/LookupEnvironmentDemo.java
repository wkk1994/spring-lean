package com.wkk.learn.spring.ioc.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * 依赖查找 {@link Environment} 示例
 * @author Wangkunkun
 * @date 2021/6/25 15:30
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);
        applicationContext.refresh();
        LookupEnvironmentDemo demo = applicationContext.getBean(LookupEnvironmentDemo.class);
        Environment environment1 = applicationContext.getBean(ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(demo.environment == environment1);
        applicationContext.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
