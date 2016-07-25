package tech.thdev.butter_knife_example.adapter.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import butterknife.BindView;
import tech.thdev.butter_knife_example.R;
import tech.thdev.butter_knife_example.adapter.RadioRecyclerAdapter;
import tech.thdev.butter_knife_example.base.adapter.view.BaseRecyclerView;
import tech.thdev.butter_knife_example.bean.RadioItem;
import tech.thdev.butter_knife_example.listener.OnRecyclerItemClickListener;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class RadioRecyclerView extends BaseRecyclerView<RadioRecyclerAdapter, RadioItem> {

    @BindView(R.id.radio_button)
    RadioButton radioButton;

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public RadioRecyclerView(ViewGroup parent, RadioRecyclerAdapter adapter, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        super(R.layout.item_radio_view, parent, adapter);

        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void onViewHolder(@Nullable final RadioItem item, final int position) {
        if (item != null) {
            radioButton.setChecked(item.isSelected);
            radioButton.setText(item.name);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getAdapter().changeSelectItem(item);

                    if (onRecyclerItemClickListener != null) {
                        onRecyclerItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
