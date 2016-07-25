package tech.thdev.recycler_lambda_example.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tae-hwan on 3/28/16.
 * <p>
 * Adapter Base view.
 */
public abstract class BaseRecyclerAdapter<DATA extends AdapterItem, VH extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<VH> {

    private Context context;

    private List<DATA> itemList;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder != null) {
            holder.onViewHolder(getItem(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) != null ? getItem(position).viewType : super.getItemViewType(position);
    }

    @Nullable
    protected DATA getItem(int position) {
        return itemList != null ? itemList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    protected List<DATA> getItemList() {
        return itemList;
    }

    public void setItemList(List<DATA> itemList) {
        setItemList(itemList, true);
    }

    public void setItemList(List<DATA> itemList, boolean isNotify) {
        this.itemList = itemList;

        if (isNotify) {
            notifyDataSetChanged();
        }
    }

    public void addItem(DATA item) {
        addItem(item, true);
    }

    public void addItem(DATA item, boolean isNotify) {
        if (item != null) {
            itemList.add(item);

            if (isNotify) {
                notifyItemChanged(item);
            }
        }
    }

    /**
     * NotifyItemChanged to DATA
     *
     * @param item
     */
    public void notifyItemChanged(DATA item) {
        if (item != null) {
            int index = itemList.indexOf(item);
            notifyItemChanged(index);
        }
    }

    /**
     * @param itemList 의 데이터를 삭제하고 {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemRangeRemoved} 함수 호출
     * @param position 아이템을 delete 해야할 위치
     */
    public void notifyItemRangeRemoved(List<DATA> itemList, int position) {
        if (itemList != null && itemList.size() > 0) {
            getItemList().removeAll(itemList);
            notifyItemRangeRemoved(position, itemList.size());
        }
    }

    /**
     * @param itemList 의 데이터를 Insert {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemRangeInserted} 함수 호출
     * @param position 아이템을 add 해야할 시작위치
     */
    public void notifyItemRangeInserted(List<DATA> itemList, int position) {
        if (itemList != null && itemList.size() > 0) {
            getItemList().addAll(position, itemList);
            notifyItemRangeInserted(position, itemList.size());
        }
    }

    protected int getItemIndex(DATA item) {
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
