package com.wkk.learn.spring.ioc.conversion;


import java.beans.PropertyEditor;

/**
 * 字符串转Properties示例
 * @author Wangkunkun
 * @date 2021/6/10 12:25
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        // 模拟Spring Framework操作
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 有一段文本 name = 你好;
        String text = "name=你好";
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());

    }
}
