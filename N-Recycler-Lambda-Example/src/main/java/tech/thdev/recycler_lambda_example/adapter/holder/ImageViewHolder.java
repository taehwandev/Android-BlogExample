package tech.thdev.recycler_lambda_example.adapter.holder;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import tech.thdev.recycler_lambda_example.Image;
import tech.thdev.recycler_lambda_example.R;
import tech.thdev.recycler_lambda_example.adapter.ImageViewRecyclerAdapter;
import tech.thdev.recycler_lambda_example.base.BaseRecyclerViewHolder;
import tech.thdev.recycler_lambda_example.listener.OnRecyclerItemLongClickListener;

/**
 * Created by Tae-hwan on 4/12/16.
 */
public class ImageViewHolder extends BaseRecyclerViewHolder<ImageViewRecyclerAdapter, Image> {


    private ImageView imageView;
    private OnRecyclerItemLongClickListener<Image> longTouchListener;

    public ImageViewHolder(ImageViewRecyclerAdapter adapter, ViewGroup parent, OnRecyclerItemLongClickListener<Image> longTouchListener) {
        super(R.layout.dialog_image, parent, adapter);

        this.longTouchListener = longTouchListener;

        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    @Override
    public void onViewHolder(@Nullable Image item, int position) {
        if (item != null) {
            imageView.setImageResource(item.res);

            imageView.setOnLongClickListener(view -> {
                Log.d("TAG", "onLongClickListener");
                if (longTouchListener != null) {
                    return longTouchListener.onItemLongClick(item, position);
                }
                return false;
            });
        }
    }
}
