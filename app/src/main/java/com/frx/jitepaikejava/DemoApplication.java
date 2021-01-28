package com.frx.jitepaikejava;

import android.app.Application;
import android.util.Log;

import com.frx.jitepaikejava.StatusLayoutDemo.statuslayout.StatusConfig;
import com.frx.jitepaikejava.StatusLayoutDemo.statuslayout.StatusConfigBean;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //设置全局状态管理
        setStatusConfig();
    }

    private void setStatusConfig() {
        StatusConfigBean loadingStatus = new StatusConfigBean.Builder()
                .setStatus(StatusConfig.STATUS_LOADING)
                .setLayout(R.layout.include_loading)
                .setAutoClick(true)
                .build();

        StatusConfigBean emptyStatus = new StatusConfigBean.Builder()
                .setStatus(StatusConfig.STATUS_EMPTY)
                .setLayout(R.layout.include_empty)
                .setAutoClick(true)
                .build();

        StatusConfigBean errorStatus = new StatusConfigBean.Builder()
                .setStatus(StatusConfig.STATUS_ERROR)
                .setLayout(R.layout.include_error)
                .setAutoClick(true)
                .build();

        StatusConfig.setGlobalStatusConfigs(new StatusConfigBean[]{loadingStatus, emptyStatus, errorStatus});
        Log.d("fmsg", "setStatusConfig");
    }
}
