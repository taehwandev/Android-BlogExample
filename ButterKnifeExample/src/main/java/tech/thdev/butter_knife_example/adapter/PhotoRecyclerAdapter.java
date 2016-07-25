package tech.thdev.butter_knife_example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import tech.thdev.butter_knife_example.adapter.model.PhotoDataModel;
import tech.thdev.butter_knife_example.adapter.view.PhotoRecyclerView;
import tech.thdev.butter_knife_example.base.adapter.BaseRecyclerAdapter;
import tech.thdev.butter_knife_example.base.adapter.view.BaseRecyclerView;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;
import tech.thdev.butter_knife_example.network.domain.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerAdapter extends BaseRecyclerAdapter<Photo> implements PhotoDataModel {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public PhotoRecyclerAdapter(Context context) {
        super(context);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public BaseRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoRecyclerView(parent, this, onRecyclerItemClickListener);
    }

    @Override
    public void add(Photo photo) {
        addItem(photo);
    }

    @Override
    public void add(Photo photo, boolean isNotify) {
        addItem(photo, isNotify);
    }

    @Override
    public void remove(int position) {
        removeItem(position);
    }

    @Override
    public Photo getPhotoItem(int position) {
        return getItem(position);
    }

    @Override
    public int getSize() {
        return getItemCount();
    }
}
