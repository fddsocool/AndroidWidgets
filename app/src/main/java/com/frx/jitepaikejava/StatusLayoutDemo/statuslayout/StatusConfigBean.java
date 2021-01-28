package com.frx.jitepaikejava.StatusLayoutDemo.statuslayout;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

public class StatusConfigBean {

    /**
     * 状态布局的status标记，比如空页面、错误页面等等
     */
    private String status;

    /**
     * 与status标记对应的布局id
     */
    @LayoutRes
    private int layout;

    /**
     * 与layout类似的作用
     */
    private View view;

    /**
     * 传入点击事件的id
     */
    @IdRes
    private int clickRes;

    private boolean autoClick = true;

    private StatusConfigBean() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getClickRes() {
        return clickRes;
    }

    public void setClickRes(int clickRes) {
        this.clickRes = clickRes;
    }

    public boolean isAutoClick() {
        return autoClick;
    }

    public void setAutoClick(boolean autoClick) {
        this.autoClick = autoClick;
    }

//    public static class Builder {
//        private String status;
//
//        @LayoutRes
//        private int layout;
//
//        private View view;
//
//        @IdRes
//        private int clickRes;
//
//        private boolean autoClick = true;
//
//        public Builder setStatus(String status) {
//            this.status = status;
//            return this;
//        }
//
//        public Builder setLayout(int layout) {
//            this.layout = layout;
//            return this;
//        }
//
//        public Builder setView(View view) {
//            this.view = view;
//            return this;
//        }
//
//        public Builder setClickRes(int clickRes) {
//            this.clickRes = clickRes;
//            return this;
//        }
//
//        public Builder setAutoClick(boolean autoClick) {
//            this.autoClick = autoClick;
//            return this;
//        }
//
//        public StatusConfigBean build() {
//            return new StatusConfigBean(this);
//        }
//    }

    public static class Builder {
        StatusConfigBean mStatusConfigBean = new StatusConfigBean();

        public Builder setStatus(String status) {
            mStatusConfigBean.status = status;
            return this;
        }

        public Builder setLayout(int layout) {
            mStatusConfigBean.layout = layout;
            return this;
        }

        public Builder setView(View view) {
            mStatusConfigBean.view = view;
            return this;
        }

        public Builder setClickRes(int clickRes) {
            mStatusConfigBean.clickRes = clickRes;
            return this;
        }

        public Builder setAutoClick(boolean autoClick) {
            mStatusConfigBean.autoClick = autoClick;
            return this;
        }

        public StatusConfigBean build() {
            return mStatusConfigBean;
        }
    }
}
