package com.frx.jitepaikejava.NoIfElseDemo;

import android.util.Log;

public class JingDongPaySupport implements IPaySupport {

    @Override
    public boolean support(String code) {
        return "jingdong".equals(code);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>support模板方法", "===发起京东支付===");
    }
}
