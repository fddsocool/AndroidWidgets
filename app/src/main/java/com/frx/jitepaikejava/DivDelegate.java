package com.frx.jitepaikejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class DivDelegate implements IDelegateAdapter<DemoBean> {

    private Context mContext;

    public DivDelegate(Context context) {
        mContext = context;
    }

    @Override
    public boolean isForViewType(DemoBean data) {
        return data.getType() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_div, viewGroup, false);
        return new WidgetDelegateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, DemoBean data) {
        WidgetDelegateViewHolder vh = (WidgetDelegateViewHolder) holder;
        vh.mTvDiv.setText(String.valueOf(data.getDemoName()));
    }

    static class WidgetDelegateViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mClRoot;
        TextView mTvDiv;

        public WidgetDelegateViewHolder(@NonNull View itemView) {
            super(itemView);
            mClRoot = itemView.findViewById(R.id.clRoot);
            mTvDiv = itemView.findViewById(R.id.tvDiv);
        }
    }
}
