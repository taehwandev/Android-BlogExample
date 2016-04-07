package tech.thdev.multiwindow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.thdev.multiwindow.adapter.holder.MultiWindowHolder;


/**
 * Created by taehwankwon on 4/4/16.
 */
public class MultiWindowAdapter extends RecyclerView.Adapter<MultiWindowHolder> {

    private ArrayList<String> itemList;

    private Context context;

    public MultiWindowAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MultiWindowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultiWindowHolder(context, parent);
    }

    @Override
    public void onBindViewHolder(MultiWindowHolder holder, int position) {
        holder.onBindViewHolder(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public void addItem(String item, boolean isNotify) {
        if (itemList != null) {
            itemList.add(0, item);
        }

        if (isNotify) {
            notifyItemInserted(0);
        }
    }
}
