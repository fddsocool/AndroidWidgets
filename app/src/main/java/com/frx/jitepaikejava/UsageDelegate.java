package com.frx.jitepaikejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class UsageDelegate implements IDelegateAdapter<DemoBean> {

    private Context mContext;
    private onItemClick<DemoBean> mOnItemClick;

    public UsageDelegate(Context context) {
        mContext = context;
    }

    public void setOnItemClick(onItemClick<DemoBean> onItemClick) {
        mOnItemClick = onItemClick;
    }

    @Override
    public boolean isForViewType(DemoBean data) {
        return data.getType() == 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_usage, viewGroup, false);
        return new UsageDelegateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, DemoBean data) {
        UsageDelegateViewHolder vh = (UsageDelegateViewHolder) holder;

        vh.mTvWidgetName.setText(String.valueOf(data.getDemoName()));
        vh.mTvWidgetDes.setText(String.valueOf(data.getDescription()));
        vh.mClRoot.setOnClickListener(v -> {
            if (mOnItemClick != null) {
                mOnItemClick.onClick(position, data);
            }
        });
    }

    static class UsageDelegateViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mClRoot;
        TextView mTvWidgetName;
        TextView mTvWidgetDes;

        public UsageDelegateViewHolder(@NonNull View itemView) {
            super(itemView);
            mClRoot = itemView.findViewById(R.id.clRoot);
            mTvWidgetName = itemView.findViewById(R.id.tvWidgetName);
            mTvWidgetDes = itemView.findViewById(R.id.tvWidgetDes);
        }
    }
}
