package com.frx.jitepaikejava.NoIfElse.payFactory;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayStrategyFactory {
    private Map<String, IFactoryPay> mPayRegisters = new HashMap<>();

    private ArrayList<IFactoryPay> mIPayArrayList;

    public PayStrategyFactory(ArrayList<IFactoryPay> IPayArrayList) {
        mIPayArrayList = IPayArrayList;
        init();
    }

    private void init() {
        if (mIPayArrayList != null) {
            for (IFactoryPay pay : mIPayArrayList) {
                pay.init(this);
            }
        }
    }

    public void register(String code, IFactoryPay iFactoryPay) {
        if (!TextUtils.isEmpty(code)) {
            mPayRegisters.put(code, iFactoryPay);
        }
    }

    public IFactoryPay get(String code) {
        return mPayRegisters.get(code);
    }
}
