package com.frx.jitepaikejava.NoIfElseDemo.ResponsibilityChain;

public abstract class PayHandler {

    private PayHandler tryNextPay;

    public abstract void pay(String pay);

    public PayHandler getTryNextPay() {
        return tryNextPay;
    }

    public void setTryNextPay(PayHandler tryNextPay) {
        this.tryNextPay = tryNextPay;
    }
}
