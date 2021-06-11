package com.wkk.learn.spring.ioc.conversion;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Properties;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现，注册自定义的PropertyEditor
 * @author Wangkunkun
 * @date 2021/6/10 15:19
 * @see PropertyEditorRegistrar
 */
// @Component  // 3.将其声明为Spring Bean
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {


    // 1.实现registerCustomEditors方法
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 2.注册自定义的PropertyEditor
        registry.registerCustomEditor(Properties.class, new StringToPropertiesPropertyEditor());
        // 以下注册有问题
        // registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
    }
}
