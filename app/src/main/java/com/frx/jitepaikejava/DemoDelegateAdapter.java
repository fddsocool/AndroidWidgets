package com.frx.jitepaikejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoDelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<DemoBean> mDemoBeans;
    private final List<IDelegateAdapter<DemoBean>> mDelegateAdapters;

    public DemoDelegateAdapter(Context context, List<DemoBean> demoBeans, List<IDelegateAdapter<DemoBean>> delegateAdapters) {
        mContext = context;
        mDemoBeans = demoBeans;
        mDelegateAdapters = delegateAdapters;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IDelegateAdapter<DemoBean> adapter = mDelegateAdapters.get(viewType);
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        IDelegateAdapter<DemoBean> adapter = mDelegateAdapters.get(viewType);
        adapter.onBindViewHolder(holder, position, mDemoBeans.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        for (IDelegateAdapter<DemoBean> adapter : mDelegateAdapters) {
            if (adapter.isForViewType(mDemoBeans.get(position))) {
                return mDelegateAdapters.indexOf(adapter);
            }
        }
        throw new RuntimeException("没有找到可以处理的Adapter");
    }

    @Override
    public int getItemCount() {
        return mDemoBeans.size();
    }
}
