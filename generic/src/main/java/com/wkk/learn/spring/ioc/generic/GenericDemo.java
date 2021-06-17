package com.wkk.learn.spring.ioc.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java泛型示例
 * @author Wangkunkun
 * @date 2021/6/15 14:59
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<String> collection = new ArrayList<String>();
        collection.add("Hello");
        collection.add("World");

        Collection temp = collection;
        temp.add(1);

        System.out.println(collection);
        System.out.println(collection.get(1));
        ParameterizedType genericSuperclass = (ParameterizedType)collection.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Stream.of(genericSuperclass.getActualTypeArguments()).forEach(System.out::println);
    }
}
