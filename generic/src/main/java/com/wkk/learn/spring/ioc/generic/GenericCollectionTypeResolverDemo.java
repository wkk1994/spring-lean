package com.wkk.learn.spring.ioc.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link GenericCollectionTypeResolver} 示例
 * @author Wangkunkun
 * @date 2021/6/17 10:46
 * @see GenericCollectionTypeResolver
 */
public class GenericCollectionTypeResolverDemo {

    private StringList stringList;
    private List<String> strings;

    public static void main(String[] args) throws NoSuchFieldException {
        // getCollectionType 返回具体化泛型参数类型集合的成员类型 = String，比如Collection<E>中的E
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        // 成员类型没有具体化返回null
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        // 获取字段为集合类型的具体化
        Field stringList = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringList));
        Field strings = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(strings));
    }
}
