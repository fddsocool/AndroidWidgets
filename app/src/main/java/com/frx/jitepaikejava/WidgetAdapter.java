package com.frx.jitepaikejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WidgetAdapter extends RecyclerView.Adapter<WidgetAdapter.WidgetAdapterViewHolder> {
    private final Context mContext;
    private final List<DemoBean> mDemoBeans;
    //监听
    private OnDemoItemClickListener mOnDemoItemClickListener;

    public WidgetAdapter(Context context, List<DemoBean> demoBeans) {
        mContext = context;
        mDemoBeans = demoBeans;
    }

    public void setOnDemoItemClickListener(OnDemoItemClickListener onDemoItemClickListener) {
        mOnDemoItemClickListener = onDemoItemClickListener;
    }

    @NonNull
    @Override
    public WidgetAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_widget, parent, false);
        return new WidgetAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WidgetAdapterViewHolder holder, int position) {
        DemoBean bean = mDemoBeans.get(position);
        holder.mTvWidgetName.setText(String.valueOf(bean.getDemoName()));
        holder.mTvWidgetDes.setText(String.valueOf(bean.getDescription()));
        holder.mClRoot.setOnClickListener(v -> {
            if (mOnDemoItemClickListener != null) {
                mOnDemoItemClickListener.itemClick(position, bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDemoBeans.size();
    }

    //文本输入监听
    public interface OnDemoItemClickListener {
        void itemClick(int position, DemoBean demoBean);
    }

    static class WidgetAdapterViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mClRoot;
        TextView mTvWidgetName;
        TextView mTvWidgetDes;

        public WidgetAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mClRoot = itemView.findViewById(R.id.clRoot);
            mTvWidgetName = itemView.findViewById(R.id.tvWidgetName);
            mTvWidgetDes = itemView.findViewById(R.id.tvWidgetDes);
        }
    }
}
