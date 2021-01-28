package com.frx.jitepaikejava.StatusLayoutDemo.statuslayout;

import com.frx.jitepaikejava.R;

import java.util.HashMap;

public class StatusConfig {

    // 全局状态布局值
    public static final String STATUS_NORMAL = "normal_content";
    public static final String STATUS_LOADING = "loading_content";
    public static final String STATUS_ERROR = "error_content";
    public static final String STATUS_EMPTY = "empty_content";

    // 全局状态布局的属性值
    public static HashMap<String, StatusConfigBean> GlobalStatusConfigs = new HashMap<>();

    public static void setGlobalStatusConfigs(StatusConfigBean[] statusConfigBeans) {
        for (StatusConfigBean statusConfigBean : statusConfigBeans) {
            GlobalStatusConfigs.put(statusConfigBean.getStatus(), statusConfigBean);
        }
    }

    //全局切换动画
    public static int GlobalInAnimation = R.anim.anim_status_layout_alpha_in;
    public static int GlobalOutAnimation = R.anim.anim_status_layout_alpha_out;

    public static void setGlobalAnimation(int inAnim, int outAnim) {
        GlobalInAnimation = inAnim;
        GlobalOutAnimation = outAnim;
    }
}
