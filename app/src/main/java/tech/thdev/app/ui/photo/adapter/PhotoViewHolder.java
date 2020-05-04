package tech.thdev.app.ui.photo.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.app.R;
import tech.thdev.app.network.domain.Photo;
import tech.thdev.simpleadapter.holder.BaseViewHolder;

public class PhotoViewHolder extends BaseViewHolder<Photo> {

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.text)
    TextView textView;

    public PhotoViewHolder(@NotNull ViewGroup parent, @NotNull OnItemClickListener onItemClickListener) {
        super(R.layout.item_photo_view, parent);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onClickItem(getAdapterPosition());
        });
    }

    @Override
    public void onBindViewHolder(@NotNull Photo photo) {
        Glide.with(getContext())
                .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", String.valueOf(photo.farm), photo.server, photo.id, photo.secret))
                .centerCrop()
                .into(imageView);

        textView.setText(photo.title);
    }
}

