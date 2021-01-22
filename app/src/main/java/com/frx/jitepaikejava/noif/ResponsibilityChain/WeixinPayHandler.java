package com.frx.jitepaikejava.noif.ResponsibilityChain;

import android.util.Log;

public class WeixinPayHandler extends PayHandler {

    @Override
    public void pay(String pay) {
        if ("weixin".equals(pay)) {
            Log.d("fmsg===>责任链模式", "===发起微信支付===");
        } else if (getTryNextPay() != null) {
            getTryNextPay().pay(pay);
        } else {
            Log.d("fmsg===>责任链模式", "找不到对应的支付方式");
        }
    }
}
