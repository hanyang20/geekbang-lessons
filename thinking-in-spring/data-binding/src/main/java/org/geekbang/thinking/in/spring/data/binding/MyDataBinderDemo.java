package org.geekbang.thinking.in.spring.data.binding;

import org.geekbang.thinking.in.spring.ioc.overview.domain.Company;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

public class MyDataBinderDemo {

//    public static void main(String[] args) {
//        User user = new User();
//
//        //1.创建dataBinder
//        DataBinder dataBinder = new DataBinder(user,"user");
//
//        Map<String, Object> source = new HashMap<>();
//        source.put("id", 1);
//        source.put("name", "小马哥");
//
//        //特性一 绑定不存在的属性时,会忽略不存在的属性
//        source.put("age", 18);
//
//        // b. PropertyValues 存在一个嵌套属性，比如 company.name
//        // DataBinder 特性二：支持嵌套属性
//        // Company company = new Company();
//        // company.setName("geekbang");
//        // user.setCompany(compay)
//
//
////        source.put("company", new Company());
//        source.put("company.name", "geek");
//
//        //2.创建PropertyValues
//        PropertyValues propertyValues = new MutablePropertyValues(source);
//
//
//        //1.ignoreUnknownFields 是否忽略未知字段，默认值：true
//        //false -> age 字段不存在user中 报错
////        dataBinder.setIgnoreUnknownFields(false);
//
//        //2.autoGrowNestedPath 是否自动增加嵌套路径，默认值：true
//        dataBinder.setAutoGrowNestedPaths(false);
//
//        //3.ignoreInvalidFields 是否忽略非法字段，默认值：false
//        //true 没效果 需增加setAutoGrowNestedPaths为 false
//        dataBinder.setIgnoreInvalidFields(true);
//        //4.setDisallowedFields 绑定字段黑名单 name不会赋值到user中
//        dataBinder.setDisallowedFields("name");
//
//        //5.user缺少city也不会报错 错误信息在BindingResult
////        dataBinder.setRequiredFields("id","name","city");
//
//        //3.绑定propertyValues
//        dataBinder.bind(propertyValues);
//
//        //4.输出user
//        System.out.println(user);
//
//        //5.结果包含错误文案 code，不会抛出异常
//        BindingResult bindingResult = dataBinder.getBindingResult();
//        System.out.println(bindingResult);
//    }


    public static void main(String[] args) {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");
        Map<String, Object> sourceMap = new HashMap<>();

        sourceMap.put("id", 1);
        sourceMap.put("name", "小马哥");
        //user中没有的属性age
        //特性一: dataBinder 默认忽略未知属性
        sourceMap.put("age", 18);

        //user包含 company.name的嵌套属性
        //特性二: dataBinder支持嵌套属性
        sourceMap.put("company", new Company());
        sourceMap.put("company.name", "蛋壳");


        PropertyValues propertyValues = new MutablePropertyValues(sourceMap);
        //是否忽略未知字段，默认值：true
//        dataBinder.setIgnoreUnknownFields(false);
        //是否自动增加嵌套路径，默认值：true
        //置为false 不报错的话必须要sourceMap.put("company", new Company());
        dataBinder.setAutoGrowNestedPaths(false);
        //        是否忽略未知字段，默认值：true
//        dataBinder.setIgnoreInvalidFields(true)
//        allowedFields 绑定字段白名单
//        dataBinder.setAllowedFields("age");
        dataBinder.setDisallowedFields("name");
        //requiredFields 必须绑定字段
//        dataBinder.setRequiredFields("city");
        dataBinder.bind(propertyValues);

        System.out.println(user);
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }
}
