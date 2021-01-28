package com.frx.jitepaikejava.StatusLayoutDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frx.jitepaikejava.R;
import com.frx.jitepaikejava.StatusLayoutDemo.statuslayout.StatusConfig;
import com.frx.jitepaikejava.StatusLayoutDemo.statuslayout.StatusConfigBean;
import com.frx.jitepaikejava.StatusLayoutDemo.statuslayout.StatusLayout;
import com.frx.jitepaikejava.utils.RxJavaUtil;

public class StatusLayoutActivity extends AppCompatActivity {

    private Button mBtnNormal;
    private Button mBtnEmpty;
    private Button mBtnLoading;
    private Button mBtnError;
    private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_layout);

        mBtnNormal = findViewById(R.id.btn_normal);
        mBtnEmpty = findViewById(R.id.btn_empty);
        mBtnLoading = findViewById(R.id.btn_loading);
        mBtnError = findViewById(R.id.btn_error);
        mStatusLayout = findViewById(R.id.status_layout);

        initView();
    }

    private void initView() {
        View errorOtherView = LayoutInflater.from(this).inflate(R.layout.include_error_other, mStatusLayout, false);
        StatusConfigBean errorOtherViewConfig = new StatusConfigBean.Builder()
                .setStatus(StatusConfig.STATUS_ERROR)
                .setView(errorOtherView)
                .setAutoClick(true)
                .build();

        Button tryBtn = errorOtherView.findViewById(R.id.btn_retry);

        StatusConfigBean normalViewConfig = new StatusConfigBean.Builder()
                .setStatus(StatusConfig.STATUS_NORMAL)
                .setLayout(R.layout.include_normal)
                .setAutoClick(true)
                .build();

        mStatusLayout.initStatus(true, true, 0, 0)
                .addLayout(normalViewConfig)
                .addLayout(errorOtherViewConfig)
                .setOnLayoutClickListener((view, status) -> {
                    Toast.makeText(this, "OnLayoutClickListener status:" + status, Toast.LENGTH_SHORT).show();
                });

        mBtnNormal.setOnClickListener(v -> {
            mStatusLayout.switchLayout(StatusConfig.STATUS_NORMAL);
        });

        mBtnEmpty.setOnClickListener(v -> {
            mStatusLayout.switchLayout(StatusConfig.STATUS_EMPTY);
        });

        mBtnLoading.setOnClickListener(v -> {
            mStatusLayout.switchLayout(StatusConfig.STATUS_LOADING);
            RxJavaUtil.runTimer(2000, new RxJavaUtil.OnRxRunTimerListener() {
                @Override
                public void onExecute(Long l) {
                    Log.d("StatusLayoutActivity", "loading:" + l);
                }

                @Override
                public void onFinish() {
                    mStatusLayout.switchLayout(StatusConfig.STATUS_NORMAL);
                }

                @Override
                public void onError(Throwable e) {
                    mStatusLayout.switchLayout(StatusConfig.STATUS_ERROR);
                }
            });
        });

        mBtnError.setOnClickListener(v -> {
            mStatusLayout.switchLayout(StatusConfig.STATUS_ERROR);
        });

        tryBtn.setOnClickListener(v -> {
            mStatusLayout.switchLayout(StatusConfig.STATUS_LOADING);
            RxJavaUtil.runTimer(2000, new RxJavaUtil.OnRxRunTimerListener() {
                @Override
                public void onExecute(Long l) {
                }

                @Override
                public void onFinish() {
                    mStatusLayout.switchLayout(StatusConfig.STATUS_NORMAL);
                }

                @Override
                public void onError(Throwable e) {
                    mStatusLayout.switchLayout(StatusConfig.STATUS_ERROR);
                }
            });
        });

        mStatusLayout.switchLayout(StatusConfig.STATUS_LOADING);
        RxJavaUtil.runTimer(2000, new RxJavaUtil.OnRxRunTimerListener() {
            @Override
            public void onExecute(Long l) {
            }

            @Override
            public void onFinish() {
                mStatusLayout.switchLayout(StatusConfig.STATUS_NORMAL);
            }

            @Override
            public void onError(Throwable e) {
                mStatusLayout.switchLayout(StatusConfig.STATUS_ERROR);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mStatusLayout.onDestroy();
        super.onDestroy();
    }
}