package com.wkk.learn.spring.ioc.dependency.injection;

import com.wkk.learn.spring.ioc.dependency.injection.entity.AutowiredUser;
import com.wkk.learn.spring.ioc.dependency.injection.entity.InjectUser;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description 自定义依赖注入注解
 * @Author Wangkunkun
 * @Date 2021/3/22 22:48
 */

public class CustomAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @AutowiredUser
    private User autowiredUser;

    @InjectUser
    private User injectUser;

   /* @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor internalAutowiredAnnotationProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired + @Inject +  新注解 @InjectUser
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectUser.class));
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return autowiredAnnotationBeanPostProcessor;
    }*/

    @Bean // 该方法必须为static
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("META-INF/xml-dependency-injection-context.xml");
        applicationContext.register(CustomAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();

        CustomAnnotationDependencyInjectionDemo bean = applicationContext.getBean(CustomAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.users: " + bean.user);
        System.out.println("demo.autowiredUser: " + bean.autowiredUser);
        System.out.println("demo.injectUser: " + bean.injectUser);
    }

}
