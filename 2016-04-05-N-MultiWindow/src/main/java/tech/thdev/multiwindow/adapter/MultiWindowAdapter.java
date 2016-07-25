package tech.thdev.multiwindow.adapter;

import android.content.Context;
import android.view.ViewGroup;

import tech.thdev.multiwindow.adapter.holder.MultiWindowViewHolder;
import tech.thdev.multiwindow.adapter.model.MultiWindowDataModel;
import tech.thdev.multiwindow.base.adapter.BaseRecyclerAdapter;
import tech.thdev.multiwindow.base.adapter.view.BaseRecyclerViewHolder;


/**
 * Created by taehwankwon on 4/4/16.
 */
public class MultiWindowAdapter extends BaseRecyclerAdapter<String> implements MultiWindowDataModel {

    public MultiWindowAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultiWindowViewHolder(parent, this);
    }

    @Override
    public void add(String name) {
        addItem(name);
    }

    @Override
    public int getSize() {
        return getItemCount();
    }
}
