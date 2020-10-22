package com.example.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注了该注解的方法不返回统一格式的JSON
 * 比如返回 thymeleaf 的页面的方法就需要添加该注解
 * 比如只需返回业务数据，不需要其他额外数据等
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotResponseBody {
}
