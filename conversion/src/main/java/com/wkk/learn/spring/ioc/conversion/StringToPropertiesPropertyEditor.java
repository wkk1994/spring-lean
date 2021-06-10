package com.wkk.learn.spring.ioc.conversion;

import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * 实现String -> Properties
 * @author Wangkunkun
 * @date 2021/6/10 12:26
 * @see PropertiesEditor
 * @see PropertyEditorSupport
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    // 1. 实现 setAsText(String) 方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)) {
            // 2. 将 String 类型转换成 Properties 类型
            Properties properties = new Properties();
            try {
                properties.load(new StringReader(text));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            // 3. 临时存储 Properties 对象
            setValue(properties);
        }
    }
}
