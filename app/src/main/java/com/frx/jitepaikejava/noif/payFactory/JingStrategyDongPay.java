package com.frx.jitepaikejava.noif.payFactory;

import android.util.Log;
import com.frx.jitepaikejava.noif.IPay;

public class JingStrategyDongPay implements IFactoryPay {

    @Override
    public void init(PayStrategyFactory factory) {
        factory.register("jingdong", this);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>策略工厂模式", "===发起京东支付===");
    }
}
