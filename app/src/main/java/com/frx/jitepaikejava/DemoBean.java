package com.frx.jitepaikejava;

public class DemoBean {

    private String demoName;

    private String description;

    private Class mIntentClass;

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
