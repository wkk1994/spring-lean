package com.wkk.learn.spring.ioc.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Java泛型示例
 * @author Wangkunkun
 * @date 2021/6/15 14:59
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<String> collection = new ArrayList<>();
        collection.add("Hello");
        collection.add("World");

        Collection temp = collection;
        temp.add(1);

        System.out.println(collection);
        System.out.println(collection.get(1));
    }
}
