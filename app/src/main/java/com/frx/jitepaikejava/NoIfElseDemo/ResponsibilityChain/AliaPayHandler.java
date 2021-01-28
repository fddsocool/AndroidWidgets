package com.frx.jitepaikejava.NoIfElseDemo.ResponsibilityChain;

import android.util.Log;

public class AliaPayHandler extends PayHandler {

    @Override
    public void pay(String pay) {
        if ("alia".equals(pay)) {
            Log.d("fmsg===>责任链模式", "===发起支付宝支付===");
        } else if (getTryNextPay() != null) {
            getTryNextPay().pay(pay);
        } else {
            Log.d("fmsg===>责任链模式", "找不到对应的支付方式");
        }
    }
}
