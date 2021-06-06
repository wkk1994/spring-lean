package com.wkk.learn.spring.ioc.validation;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * {@link Errors } 错误文案示例
 * @author Wangkunkun
 * @date 2021/6/6 14:45
 * @see Errors
 * @see StaticMessageSource
 */
public class ErrorsMessageDemo {

    public static void main(String[] args) {
        User user = new User();
        // 1. 选择 Errors - BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");

        // 2. 调用 reject 或 rejectValue
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");
        // 3. 获取 Errors 中 ObjectError 和 FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();
        MessageSource messageSource = createMessageSource();
        // 4. 通过 ObjectError 和 FieldError 中的 code 和 args 来关联 MessageSource 实现
        for (ObjectError allError : allErrors) {
            String message = messageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User所有属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null.");
        return messageSource;
    }
}
