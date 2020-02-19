package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2020/2/19.
 *
 * @author Wang Qin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetValue {

    Class<?> beanClass();

    String param();

    String method();

    String targetFiled();

    Class<?> targetClass();

}
