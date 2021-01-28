package com.frx.jitepaikejava.NoIfElseDemo;

import android.util.Log;

@PayCode(value = "jingdong", name = "京东支付")
public class JingDongPay implements IPay {
    @Override
    public void pay() {
        Log.d("fmsg===>PayCode注解", "===发起京东支付===");
    }
}
