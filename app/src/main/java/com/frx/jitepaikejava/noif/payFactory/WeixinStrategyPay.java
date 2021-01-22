package com.frx.jitepaikejava.noif.payFactory;

import android.util.Log;

import com.frx.jitepaikejava.noif.IPay;

public class WeixinStrategyPay implements IFactoryPay {

    @Override
    public void init(PayStrategyFactory factory) {
        factory.register("weixin", this);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>策略工厂模式", "===发起微信支付===");
    }
}
