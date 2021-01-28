package com.frx.jitepaikejava.NoIfElseDemo;

import android.util.Log;

public class WeixinPaySupport implements IPaySupport {

    @Override
    public boolean support(String code) {
        return "weixin".equals(code);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>support模板方法", "===发起微信支付===");
    }
}
