package com.frx.jitepaikejava.noif.payFactory;

/**
 * 支付接口
 */
public interface IFactoryPay {

    void init(PayStrategyFactory factory);

    void pay();
}
