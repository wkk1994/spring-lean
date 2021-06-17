package com.wkk.learn.spring.ioc.generic;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

/**
 * {@link ResolvableType} 示例
 * @author Wangkunkun
 * @date 2021/6/17 14:05
 * @see ResolvableType
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) throws NoSuchFieldException {
        ResolvableType resolvableType = ResolvableType.forClass(ResolvableTypeDemo.class);
        System.out.println(resolvableType);
        // 转换为Collection类型，必须实现了Collection，否则为？
        System.out.println(resolvableType.asCollection());

        // StringList -> ArrayList -> AbstractList -> AbstractCollection -> Collection
        ResolvableType stringListResolvableType = ResolvableType.forClass(StringList.class);
        System.out.println(stringListResolvableType.getSuperType());// ArrayList
        System.out.println(stringListResolvableType.getSuperType().getSuperType());// AbstractList
        System.out.println(stringListResolvableType.getSuperType().getSuperType().getSuperType());// AbstractCollection

        // 获取Raw Type
        System.out.println(stringListResolvableType.asCollection().resolve());
        // 获取泛型参数类型
        System.out.println(stringListResolvableType.asCollection().getGeneric(0));

        System.out.println("================");
        example();
    }

    private HashMap<Integer, List<String>> myMap;

    public static void example() throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
        t.getSuperType(); // AbstractMap<Integer, List<String>>
        t.asMap(); // Map<Integer, List<String>>
        t.getGeneric(0).resolve(); // Integer
        t.getGeneric(1).resolve(); // List
        t.getGeneric(1); // List<String>
        t.resolveGeneric(1, 0); // String
        System.out.println("-----------");
    }
}
