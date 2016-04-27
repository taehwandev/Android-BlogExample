package tech.thdev.butter_knife_example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import tech.thdev.butter_knife_example.adapter.model.RadioDataModel;
import tech.thdev.butter_knife_example.adapter.view.RadioRecyclerView;
import tech.thdev.butter_knife_example.base.adapter.BaseRecyclerAdapter;
import tech.thdev.butter_knife_example.base.adapter.view.BaseRecyclerView;
import tech.thdev.butter_knife_example.bean.RadioItem;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class RadioRecyclerAdapter extends BaseRecyclerAdapter<RadioItem> implements RadioDataModel {

    private RadioItem selectItem = null;

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public RadioRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RadioRecyclerView(parent, this, onRecyclerItemClickListener);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        this.onRecyclerItemClickListener = listener;
    }

    @Override
    public void add(RadioItem name) {
        addItem(name);
    }

    @Override
    public RadioItem remove(int position) {
        return removeItem(position);
    }

    @Override
    public RadioItem getRadioItem(int position) {
        return getItem(position);
    }

    @Override
    public int getSize() {
        return getItemCount();
    }

    public void changeSelectItem(RadioItem radioItem) {
        if (selectItem != null) {
            selectItem.isSelected = false;
            notifyItemChanged(selectItem);
        }

        selectItem = radioItem;
        selectItem.isSelected = true;
    }
}
