package com.frx.jitepaikejava.StatusLayoutDemo.statuslayout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewAnimator;

import androidx.annotation.AnimRes;

import java.util.HashMap;

public class StatusLayout extends ViewAnimator {

    protected Context mContext;
    private final LayoutInflater mLayoutInflater;

    /**
     * 初始化成功
     */
    private boolean isInit = false;

    /**
     * 属性集合
     */
    private final HashMap<String, StatusConfigBean> mStatusConfig;

    /**
     *
     */
    private int mCurrentPosition = 0;

    /**
     * 布局点击事件
     */
    private OnLayoutClickListener mOnLayoutClickListener;

    public interface OnLayoutClickListener {
        /**
         * @param view   add的时候传入的资源文件转换的view，要在回调中做操作可以通过操作view来实现
         * @param status 当前点击的布局代表的status
         */
        void OnLayoutClick(View view, String status);
    }

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mStatusConfig = new HashMap<>();
    }

    /**
     * 获取当前布局index
     *
     * @return 当前布局index
     */
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    /**
     * 设置布局点击事件
     *
     * @param onLayoutClickListener OnLayoutClickListener
     */
    public void setOnLayoutClickListener(OnLayoutClickListener onLayoutClickListener) {
        mOnLayoutClickListener = onLayoutClickListener;
    }

    /**
     * 设置动画
     *
     * @param inRes  进入
     * @param outRes 退出
     */
    public void setAnimation(@AnimRes int inRes, @AnimRes int outRes) {
        setInAnimation(mContext, inRes);
        setOutAnimation(mContext, outRes);
    }

    /**
     * 初始化
     *
     * @param isUseGlobalConfig 是否使用全局设置
     * @return StatusLayout
     */
    public StatusLayout initStatus(boolean isUseGlobalConfig) {
        //判断初始化
        if (!isInit) {
            //如果有全局设置，延迟到切换布局时再添加到statuslayout中
            if (isUseGlobalConfig) {
                //加载全局属性
                for (String key : StatusConfig.GlobalStatusConfigs.keySet()) {
                    if (!mStatusConfig.containsKey(key)) {
                        addLayout(StatusConfig.GlobalStatusConfigs.get(key));
                    }
                }
            }
            isInit = true;
        }
        return this;
    }

    /**
     * 初始化，自定义动画
     *
     * @param isUseGlobalConfig    是否使用全局设置
     * @param isUseGlobalAnimation 是否使用全局动画设置
     * @param inRes                进入动画
     * @param outRes               退出动画
     * @return StatusLayout
     */
    public StatusLayout initStatus(boolean isUseGlobalConfig, boolean isUseGlobalAnimation, @AnimRes int inRes, @AnimRes int outRes) {
        //判断初始化
        if (!isInit) {
            //如果有全局设置，延迟到切换布局时再添加到statuslayout中
            if (isUseGlobalConfig) {
                //加载全局属性
                for (String key : StatusConfig.GlobalStatusConfigs.keySet()) {
                    if (!mStatusConfig.containsKey(key)) {
                        addLayout(StatusConfig.GlobalStatusConfigs.get(key));
                    }
                }
            }
            if (isUseGlobalAnimation) {
                setAnimation(StatusConfig.GlobalInAnimation, StatusConfig.GlobalOutAnimation);
            } else {
                setAnimation(inRes, outRes);
            }
            isInit = true;
        }
        return this;
    }

    /**
     * 添加布局
     *
     * @param configBean 布局配置
     * @return StatusLayout
     */
    public StatusLayout addLayout(StatusConfigBean configBean) {
        if (configBean == null) {
            throw new NullPointerException("StatusConfigBean不能为空");
        }

        try {
            //为view赋值
            if (configBean.getView() == null) {
                configBean.setView(mLayoutInflater.inflate(configBean.getLayout(), this, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("赋值view错误");
        }

        if (configBean.isAutoClick()) {
            //获取传入view和其点击事件
            View clickView = configBean.getView().findViewById(configBean.getClickRes());
            //如果点击事件为空，则布局自身处理点击事件
            if (clickView == null) {
                clickView = configBean.getView();
            }
            clickView.setOnClickListener(getClickListener(clickView, configBean.getStatus()));
        }

        //判断当前添加的布局是否已经被添加过
        int index = findLayoutIndex(configBean.getStatus());
        //为布局添加tag
        configBean.getView().setTag(configBean.getStatus());
        //如果同一个status已经被添加过，则替换为新的view，否则则添加view
        if (index >= 0) {
            removeViewAt(index);
            addView(configBean.getView(), index);
        } else {
            addView(configBean.getView());
        }
        mStatusConfig.put(configBean.getStatus(), configBean);
        return this;
    }

    /**
     * 切换指定布局
     * 不指定status时则切换StatusConfig.STATUS_NORMAL对应的布局
     *
     * @param status 布局所代表的状态
     */
    public void switchLayout(String status) {
        if (TextUtils.isEmpty(status)) {
            status = StatusConfig.STATUS_NORMAL;
        }

        //切换布局
        int index = findLayoutIndex(status);
        Log.e("StatusLayout", "status:" + status + "，未找到对应的布局");
        if (index >= 0 && mCurrentPosition != index) {
            setDisplayedChild(index);
            mCurrentPosition = index;
        }
    }

    /**
     * 获取指定布局index
     * 不指定status时则获取StatusConfig.STATUS_NORMAL对应的布局index
     *
     * @param status 布局状态
     * @return index
     */
    public int findLayoutIndex(String status) {
        if (TextUtils.isEmpty(status)) {
            status = StatusConfig.STATUS_NORMAL;
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (TextUtils.equals((CharSequence) getChildAt(i).getTag(), status)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 退出页面时必须清空子view
     */
    public void onDestroy() {
        removeAllViews();
    }

    private View.OnClickListener getClickListener(View view, String status) {
        return v -> {
            if (mOnLayoutClickListener != null) {
                mOnLayoutClickListener.OnLayoutClick(view, status);
            }
        };
    }
}
