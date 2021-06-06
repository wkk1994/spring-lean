package com.wkk.learn.spring.ioc.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Bean Validation 整合示例
 * @author Wangkunkun
 * @date 2021/6/6 21:56
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-validation-context.xml");

        Validator validator = applicationContext.getBean(Validator.class);
        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        User user = new User();
        userProcessor.process(user);
        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void process(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User {
        @NotNull
        private String name;

        public User() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
