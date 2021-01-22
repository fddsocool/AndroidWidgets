package com.frx.jitepaikejava;

public class DemoBean {

    //0=>标志
    //1=>控件
    //2=>用法
    private int type;

    private String demoName;

    private String description;

    private Class mIntentClass;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getIntentClass() {
        return mIntentClass;
    }

    public void setIntentClass(Class intentClass) {
        mIntentClass = intentClass;
    }
}
