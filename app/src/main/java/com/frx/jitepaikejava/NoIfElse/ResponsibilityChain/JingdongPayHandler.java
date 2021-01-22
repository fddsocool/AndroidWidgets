package com.frx.jitepaikejava.NoIfElse.ResponsibilityChain;

import android.util.Log;

public class JingdongPayHandler extends PayHandler {

    @Override
    public void pay(String pay) {
        if ("jingdong".equals(pay)) {
            Log.d("fmsg===>责任链模式", "===发起京东支付===");
        } else if (getTryNextPay() != null) {
            getTryNextPay().pay(pay);
        } else {
            Log.d("fmsg===>责任链模式", "找不到对应的支付方式");
        }
    }
}
