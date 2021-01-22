package com.frx.jitepaikejava.noif;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断是那个支付方式
 * 实际开发中可以根据需求传入特定的变量来判断
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayCode {
    String value();

    String name();
}
