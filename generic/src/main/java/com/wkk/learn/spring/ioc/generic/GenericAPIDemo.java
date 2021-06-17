package com.wkk.learn.spring.ioc.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java泛型API示例
 * @author Wangkunkun
 * @date 2021/6/15 17:46
 */
public class GenericAPIDemo {
    public static void main(String[] args) {
        // 原始类型 primitive types：int long float
        Class intClass = int.class;
        // 数组类型 array types：int[] object[]
        Class objectClass = Object[].class;
        // 原始类型 raw types：String Integer
        Class<String> stringClass = String.class;
        // 泛型参数类型 parameterized type
        ParameterizedType genericSuperclass = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(genericSuperclass);
        // 泛型类型变量 Type Variable
        Stream.of(genericSuperclass.getActualTypeArguments()).forEach(System.out::println);
    }
}
