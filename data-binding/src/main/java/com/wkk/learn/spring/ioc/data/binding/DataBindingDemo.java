package com.wkk.learn.spring.ioc.data.binding;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  {@link DataBinder} 参数控制示例
 * @author Wangkunkun
 * @date 2021/6/8 17:11
 */
public class DataBindingDemo {

    public static void main(String[] args) {

        // 0.创建对象
        User user = new User();
        // 1.创建 DataBinder
        DataBinder dataBinder  = new DataBinder(user, "user");

        // 2.构造PropertyValue
        Map<String, Object> propertyValuesMap = new HashMap<>();
        propertyValuesMap.put("id", "123");
        propertyValuesMap.put("name", "你好");
        PropertyValues propertyValues = new MutablePropertyValues(propertyValuesMap);
        dataBinder.bind(propertyValues);

        System.out.println(user);
    }
}
