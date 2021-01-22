package com.frx.jitepaikejava;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 多条目RecyclerView适配器委托
 *
 * @param <T>
 */
public interface IDelegateAdapter<T> {

    // 查找委托时调用的方法，返回自己能处理的类型即可。
    boolean isForViewType(T data);

    // 用于委托Adapter的onCreateViewHolder方法
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position, T data);
}
