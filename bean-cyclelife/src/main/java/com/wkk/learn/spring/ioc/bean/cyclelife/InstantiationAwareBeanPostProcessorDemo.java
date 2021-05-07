package com.wkk.learn.spring.ioc.bean.cyclelife;

import com.wkk.learn.spring.ioc.overview.dependency.model.SuperUser;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * @Description {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInitialization(Object, String)} Bean实例化前阶段示例
 * @Author Wangkunkun
 * @Date 2021/5/4 22:46
 */
public class InstantiationAwareBeanPostProcessorDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] localPath = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        reader.loadBeanDefinitions(localPath);
        // 通过 Bean Id 和类型进行依赖查找
        User user1 = beanFactory.getBean("user", User.class);
        System.out.println(user1);
        User user2 = beanFactory.getBean("superUser", User.class);
        System.out.println(user2);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    /**
     * 自定义Bean实例化前阶段
     */
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                // "user" 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
                ((User) bean).setId("12345678");
                ((User) bean).setName("asdhjf;kas;lfaj");
                return false;
            }
            return true;
        }

        /**
         * Spring Bean属性赋值前阶段，将 {@link PropertyValues} 进行修改
         * @param pvs
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                MutablePropertyValues mutablePropertyValues = new MutablePropertyValues(pvs);
                mutablePropertyValues.add("number", "123");
                if(mutablePropertyValues.contains("description")) {
                    mutablePropertyValues.removePropertyValue("description");
                    mutablePropertyValues.add("description", "The User Holder V2");
                }
                return mutablePropertyValues;
            }
            return null;
        }
    }
}
