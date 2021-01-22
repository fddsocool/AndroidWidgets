package com.frx.jitepaikejava.noif;

/**
 * 模板方法判断需要有固定的模板值
 */
public interface IPaySupport {
    boolean support(String code);

    void pay();
}
