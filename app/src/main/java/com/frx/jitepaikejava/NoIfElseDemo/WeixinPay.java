package com.frx.jitepaikejava.NoIfElseDemo;

import android.util.Log;

@PayCode(value = "weixin", name = "微信支付")
public class WeixinPay implements IPay {
    @Override
    public void pay() {
        Log.d("fmsg===>PayCode注解", "===发起微信支付===");
    }
}
