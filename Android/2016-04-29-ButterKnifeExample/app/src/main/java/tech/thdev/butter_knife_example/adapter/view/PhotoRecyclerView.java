package tech.thdev.butter_knife_example.adapter.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.adapter.PhotoRecyclerAdapter;
import tech.thdev.butter_knife_example.base.adapter.view.BaseRecyclerView;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;
import tech.thdev.butter_knife_example.network.domain.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerView extends BaseRecyclerView<PhotoRecyclerAdapter, Photo> {

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.text)
    TextView textView;

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public PhotoRecyclerView(ViewGroup parent, PhotoRecyclerAdapter adapter, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        super(R.layout.item_photo_view, parent, adapter);

        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void onViewHolder(@Nullable Photo item, final int position) {
        if (item != null) {
            Glide.with(getContext())
                    .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", String.valueOf(item.farm), item.server, item.id, item.secret))
                    .centerCrop()
                    .crossFade()
                    .into(imageView);

            textView.setText(item.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemClickListener != null) {
                        onRecyclerItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
