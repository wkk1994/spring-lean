package com.wkk.learn.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 层次性依赖查找示例
 * @Author Wangkunkun
 * @Date 2021/3/21 12:28
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        // 1.查找Parent BeanFactory
        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();
        System.out.println("BeanFactory 的 Parent BeanFactory : " + configurableListableBeanFactory.getParentBeanFactory());
        // 2.设置Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        configurableListableBeanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("----" + applicationContext.getBean("user"));
        System.out.println("BeanFactory 的 Parent BeanFactory : " + configurableListableBeanFactory.getParentBeanFactory());
        displayContainsLocalBean(configurableListableBeanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");
        displayContainsBean(configurableListableBeanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        applicationContext.close();
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    public static HierarchicalBeanFactory createBeanFactory() {
        HierarchicalBeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        return beanFactory;
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }
}
