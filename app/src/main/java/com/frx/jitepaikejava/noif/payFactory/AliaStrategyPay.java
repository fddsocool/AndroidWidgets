package com.frx.jitepaikejava.noif.payFactory;

import android.util.Log;

public class AliaStrategyPay implements IFactoryPay {

    @Override
    public void init(PayStrategyFactory factory) {
        factory.register("alia", this);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>策略工厂模式", "===发起支付宝支付===");
    }
}
