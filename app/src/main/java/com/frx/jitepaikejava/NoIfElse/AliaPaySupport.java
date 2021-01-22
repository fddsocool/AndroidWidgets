package com.frx.jitepaikejava.NoIfElse;

import android.util.Log;

public class AliaPaySupport implements IPaySupport {

    @Override
    public boolean support(String code) {
        return "alia".equals(code);
    }

    @Override
    public void pay() {
        Log.d("fmsg===>support模板方法", "===发起支付宝支付===");
    }
}
