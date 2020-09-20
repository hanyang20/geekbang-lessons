/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geekbang.thinking.in.spring.generic.my;

import org.geekbang.thinking.in.spring.generic.StringList;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.resolveReturnType;
import static org.springframework.core.GenericTypeResolver.resolveReturnTypeArgument;

/**
 * {@link GenericTypeResolver} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see GenericTypeResolver
 * @since
 */
public class MyGenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MyGenericTypeResolverDemo.class.getMethod("getString");
        // String 是 Comparable<String> 具体化
        Class<?> getStringList = GenericTypeResolver.resolveReturnType(method, MyGenericTypeResolverDemo.class);
        for (Class<?> aClass : GenericTypeResolver.resolveTypeArguments(MyGenericTypeResolverDemo.class, List.class)) {
            System.out.println(aClass);
        }

        Class<?> aClass = GenericTypeResolver.resolveReturnTypeArgument(method, Comparable.class);
        System.out.println(getStringList);
        System.out.println(aClass);
        // ArrayList<Object> 是 List 泛型参数类型的具体化
        Method method2 = MyGenericTypeResolverDemo.class.getMethod("getList");

        Class<?> getStringList2 = GenericTypeResolver.resolveReturnType(method2, MyGenericTypeResolverDemo.class);
        Class<?> aClass2 = GenericTypeResolver.resolveReturnTypeArgument(method2, List.class);
        System.out.println(getStringList2);
        System.out.println(aClass2);

        // StringList 也是 List 泛型参数类型的具体化
        Method method3 = MyGenericTypeResolverDemo.class.getMethod("getStringList");

        Class<?> getStringList3 = GenericTypeResolver.resolveReturnType(method3, MyGenericTypeResolverDemo.class);
        Class<?> aClass3 = GenericTypeResolver.resolveReturnTypeArgument(method3, List.class);
        System.out.println(getStringList3);
        System.out.println(aClass3);

        // 具备 ParameterizedType 返回，否则 null

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }


    public static StringList getStringList() {
        return null;
    }

    public static  ArrayList<String> getList() { // 泛型参数类型具体化
        return null;
    }

    public static String getString() {
        return null;
    }

}
