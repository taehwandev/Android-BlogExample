package tech.thdev.recycler_lambda_example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import tech.thdev.recycler_lambda_example.Image;
import tech.thdev.recycler_lambda_example.adapter.holder.ImageViewHolder;
import tech.thdev.recycler_lambda_example.base.BaseRecyclerAdapter;
import tech.thdev.recycler_lambda_example.listener.OnRecyclerItemLongClickListener;

/**
 * Created by Tae-hwan on 4/12/16.
 */
public class ImageViewRecyclerAdapter extends BaseRecyclerAdapter<Image, ImageViewHolder> {

    private OnRecyclerItemLongClickListener<Image> longTouchListener;

    public ImageViewRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(this, parent, longTouchListener);
    }

    public void setOnTouchLongClickListener(OnRecyclerItemLongClickListener<Image> longTouchListener) {
        this.longTouchListener = longTouchListener;
    }
}
