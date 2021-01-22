package com.frx.jitepaikejava.noif.ResponsibilityChain;

import android.util.Log;

import java.util.ArrayList;

public class PayChain {

    private ArrayList<PayHandler> mPayHandlers;

    public PayChain() {
        mPayHandlers = new ArrayList<>();
        AliaPayHandler aliaPayHandler = new AliaPayHandler();
        WeixinPayHandler weixinPayHandler = new WeixinPayHandler();
        JingdongPayHandler jingdongPayHandler = new JingdongPayHandler();
        mPayHandlers.add(aliaPayHandler);
        mPayHandlers.add(weixinPayHandler);
        mPayHandlers.add(jingdongPayHandler);

    }

    public void execute(String code) {
        Log.d("fmsg===>责任链模式", "当前支付code:" + code);
        for (int i = 0; i < mPayHandlers.size() - 1; i++) {
            mPayHandlers.get(i).setTryNextPay(mPayHandlers.get(i + 1));
        }
        mPayHandlers.get(0).pay(code);
    }
}
