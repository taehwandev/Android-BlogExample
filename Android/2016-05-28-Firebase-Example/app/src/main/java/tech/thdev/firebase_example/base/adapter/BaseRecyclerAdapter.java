package tech.thdev.firebase_example.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tech.thdev.firebase_example.base.adapter.holder.BaseRecyclerViewHolder;

/**
 * Created by Tae-hwan on 5/27/16.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder>  {

    private Context context;

    private List<T> itemList;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        itemList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (holder != null) {
            holder.onViewHolder(getItem(position), position);
        }
    }

    @Nullable
    protected T getItem(int position) {
        return itemList != null ? itemList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    protected List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        setItemList(itemList, true);
    }

    public void setItemList(List<T> itemList, boolean isNotify) {
        this.itemList = itemList;

        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public T removeItem(int position) {
        return removeItem(position, true);
    }

    public T removeItem(int position, boolean isNotify) {
        T item = getItem(position);
        if (itemList != null) {
            itemList.remove(item);
        }

        if (isNotify) {
            notifyItemRemoved(position);
        }
        return item;
    }

    public void addItem(T item) {
        addItem(item, true);
    }

    public void addItem(T item, boolean isNotify) {
        if (itemList != null) {
            itemList.add(item);

            if (isNotify) {
                notifyItemChanged(item);
            }
        }
    }

    public void notifyItemChanged(T item) {
        if (itemList != null) {
            int index = itemList.indexOf(item);
            notifyItemChanged(index);
        }
    }

    /**
     * @param itemList 의 데이터를 삭제하고 {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemRangeRemoved} 함수 호출
     * @param position 아이템을 delete 해야할 위치
     */
    public void notifyItemRangeRemoved(List<T> itemList, int position) {
        if (itemList != null && itemList.size() > 0) {
            getItemList().removeAll(itemList);
            notifyItemRangeRemoved(position, itemList.size());
        }
    }

    /**
     * @param itemList 의 데이터를 Insert {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemRangeInserted} 함수 호출
     * @param position 아이템을 add 해야할 시작위치
     */
    public void notifyItemRangeInserted(List<T> itemList, int position) {
        if (itemList != null && itemList.size() > 0) {
            getItemList().addAll(position, itemList);
            notifyItemRangeInserted(position, itemList.size());
        }
    }

    protected int getItemIndex(T item) {
        return itemList != null ? itemList.indexOf(item) : -1;
    }

    public Context getContext() {
        return context;
    }

    public void clear() {
        if (itemList != null) {
            itemList.clear();
        }
    }
}