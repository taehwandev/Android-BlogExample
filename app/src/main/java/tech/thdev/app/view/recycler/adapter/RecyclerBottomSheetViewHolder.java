package tech.thdev.app.view.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.app.R;
import tech.thdev.app.data.BottomData;

/**
 * Created by Tae-hwan on 09/12/2016.
 */

public class RecyclerBottomSheetViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_icon) ImageView imgIcon;
    @BindView(R.id.tv_title) TextView tvTitle;

    public RecyclerBottomSheetViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_recycler_bottom_sheet, parent, false));

        ButterKnife.bind(this, itemView);
    }

    public void onBind(BottomData item, int position) {
        imgIcon.setImageResource(item.iconRes);
        tvTitle.setText(item.title);
    }
}
