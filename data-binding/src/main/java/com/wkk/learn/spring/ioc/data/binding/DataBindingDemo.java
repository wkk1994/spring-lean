package com.wkk.learn.spring.ioc.data.binding;

import com.wkk.learn.spring.ioc.overview.dependency.model.Company;
import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
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

        // a.当PropertyValues中存在User中没有的属性时
        // DataBinder 特性一 : 忽略未知的属性
        propertyValuesMap.put("not_has_property", "没有的属性");
        // 调整IgnoreUnknownFields属性 true（默认） -> false，抛出错误
        // Bean property 'not_has_property' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
        //dataBinder.setIgnoreUnknownFields(false);

        // b.当PropertyValues存在嵌套属性时
        // DataBinder 特性二 : 支持嵌套属性
        propertyValuesMap.put("company.name", "公司名称");
        // 调整autoGrowNestedPaths属性 true（默认） -> false，抛出错误 Value of nested property 'company' is null
        dataBinder.setAutoGrowNestedPaths(false);
        // 添加company属性就不会报错了
        propertyValuesMap.put("company", new Company());

        // c.通过disallowedFields属性控制属性绑定
        dataBinder.setDisallowedFields("name");

        // 设置必须字段
        dataBinder.setRequiredFields("city");
        PropertyValues propertyValues = new MutablePropertyValues(propertyValuesMap);
        // 3.绑定属性
        dataBinder.bind(propertyValues);
        System.out.println(user);

        // 4. 获取绑定结果（结果包含错误文案 code，不会抛出异常）
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);
    }
}
