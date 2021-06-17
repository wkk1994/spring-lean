package com.wkk.learn.spring.ioc.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link GenericTypeResolver} 示例
 *
 * @author Wangkunkun
 * @date 2021/6/16 17:01
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        // String 是 Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString", null);
        // 这里resolveReturnTypeArgument没有值
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList", null);
        // 这里resolveReturnTypeArgument有值，泛型参数必须具体化才有值。
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList", null);

        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }


    public String getString() {
        return null;
    }

    public List<String> getStringList() {
        return null;
    }

    public <T> List<T> getList() {
        return null;
    }

    public static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s \n", methodName, containingClass.getSimpleName(), returnType);
        Class<?> resolveReturnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s \n", methodName, genericIfc.getSimpleName(), resolveReturnTypeArgument);

    }

    static class StringList extends ArrayList<String> {

    }
}
