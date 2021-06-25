package com.wkk.learn.spring.ioc.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入 {@link Environment} 示例
 * @author Wangkunkun
 * @date 2021/6/25 14:57
 * @see Environment
 */
public class InjectionEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    @Autowired
    private Environment environment1;

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationContext applicationContext1;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionEnvironmentDemo.class);
        applicationContext.refresh();
        InjectionEnvironmentDemo demo = applicationContext.getBean(InjectionEnvironmentDemo.class);
        System.out.println(demo.environment == demo.environment1);
        System.out.println(demo.environment == demo.applicationContext.getEnvironment());
        System.out.println(demo.applicationContext1 == demo.applicationContext);
        System.out.println(applicationContext == demo.applicationContext);
        System.out.println(applicationContext.getEnvironment() == demo.applicationContext.getEnvironment());
        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
