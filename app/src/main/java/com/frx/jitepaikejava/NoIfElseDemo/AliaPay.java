package com.frx.jitepaikejava.NoIfElseDemo;

import android.util.Log;

@PayCode(value = "alia", name = "支付宝支付")
public class AliaPay implements IPay {
    @Override
    public void pay() {
        Log.d("fmsg===>PayCode注解", "===发起支付宝支付===");
    }
}
