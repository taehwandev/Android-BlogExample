package tech.thdev.recycler_lambda_example.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tae-hwan on 3/28/16.
 */
public abstract class BaseRecyclerViewHolder<AD extends BaseRecyclerAdapter, DATA extends AdapterItem> extends RecyclerView.ViewHolder {

    private AD adapter;

    public BaseRecyclerViewHolder(AD adapter, View itemView) {
        super(itemView);

        this.adapter = adapter;
    }

    public BaseRecyclerViewHolder(@LayoutRes int layoutResId, ViewGroup parent, AD adapter) {
        this(adapter, LayoutInflater.from(adapter.getContext()).inflate(layoutResId, parent, false));
    }

    public abstract void onViewHolder(final @Nullable DATA item, int position);

    protected AD getAdapter() {
        return adapter;
    }

    protected Context getContext() {
        return adapter.getContext();
    }
}
