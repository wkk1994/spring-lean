package com.wkk.learn.spring.bean.scope;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @Description Spring Bean singleton prototype作用域示例代码
 * @Author Wangkunkun
 * @Date 2021/5/1 11:14
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    @Primary
    private User singletonUser() {
        User user = new User();
        user.setId(String.valueOf(System.nanoTime()));
        return user;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private User prototypeUser() {
        User user = new User();
        user.setId(String.valueOf(System.nanoTime()));
        return user;
    }

    @Autowired
    private User singletonUser1;
    @Autowired
    private User singletonUser2;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private Map<String, User> users;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    // 在这里可以进行bean的清扫工作，但是这样做会产生问题的不建议。
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });
        applicationContext.refresh();
        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);
        applicationContext.close();
    }


    /**
     * Bean作用域之依赖查找
     *
     * @param applicationContext
     */
    private static void scopedBeansByLookup(ApplicationContext applicationContext) {
        User singletonUser1 = applicationContext.getBean("singletonUser", User.class);
        User singletonUser2 = applicationContext.getBean("singletonUser", User.class);
        User prototypeUser1 = applicationContext.getBean("prototypeUser", User.class);
        User prototypeUser2 = applicationContext.getBean("prototypeUser", User.class);
        System.out.println("singletonUser1 : " + singletonUser1);
        System.out.println("singletonUser2 : " + singletonUser2);
        System.out.println("prototypeUser1 : " + prototypeUser1);
        System.out.println("prototypeUser2 : " + prototypeUser2);

    }

    /**
     * Bean作用域之依赖注入
     *
     * @param applicationContext
     */
    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser1 : " + bean.singletonUser1);
        System.out.println("singletonUser2 : " + bean.singletonUser2);
        System.out.println("prototypeUser1 : " + bean.prototypeUser1);
        System.out.println("prototypeUser2 : " + bean.prototypeUser2);
        System.out.println("users : " + bean.users);
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(entry.getKey());
            if(beanDefinition.isPrototype()) {
                System.out.printf("开始执行beanName：%s的销毁方法", entry.getKey());
                entry.getValue().destroy();
            }
        }
    }
}
