package com.wkk.learn.spring.ioc.validation;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.List;
import java.util.Locale;

import static com.wkk.learn.spring.ioc.validation.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义 {@link Validator} 示例
 * @author Wangkunkun
 * @date 2021/6/6 15:22
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1.创建Validator
        Validator validator = new UserValidator();
        // 2.判断是否支持的校验类型
        User user = new User();
        System.out.println("user 对象是否被 UserValidator 支持检验：" + validator.supports(user.getClass()));
        // 3.创建Errors对象并执行校验
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        // 4.创建MessageSource
        MessageSource messageSource = createMessageSource();
        // 5.输出所有错误文案
        List<ObjectError> allErrors = errors.getAllErrors();
        for (ObjectError allError : allErrors) {
            String message = messageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmpty(errors, "id", "id.required");
            ValidationUtils.rejectIfEmpty(errors, "name", "name.required");

        }
    }
}
