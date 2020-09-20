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
package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} Demo
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ResolvableType
 * @since
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        System.out.println(resolvableType.getSuperType());// ArrayList
        System.out.println(resolvableType.getSuperType().getSuperType());// AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // 获取 Raw Type
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型

//        ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
//        t.getSuperType(); // AbstractMap&lt;Integer, List&lt;String&gt;&gt;
//        t.asMap(); // Map&lt;Integer, List&lt;String&gt;&gt;
//        t.getGeneric(0).resolve(); // Integer
//        t.getGeneric(1).resolve(); // List
//        t.getGeneric(1); // List&lt;String&gt;
//        t.resolveGeneric(1, 0); // String
    }
}
