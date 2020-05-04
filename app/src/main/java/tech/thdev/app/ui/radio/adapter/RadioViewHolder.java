package tech.thdev.app.ui.radio.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.app.R;
import tech.thdev.app.data.RadioItem;
import tech.thdev.simpleadapter.holder.BaseViewHolder;

public class RadioViewHolder extends BaseViewHolder<RadioItem> {

    @BindView(R.id.radio_button)
    RadioButton radioButton;

    public RadioViewHolder(@NotNull ViewGroup parent, @NotNull OnItemClickListener onItemClickListener) {
        super(R.layout.item_radio_view, parent);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickItem(getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindViewHolder(@NotNull RadioItem radioItem) {
        radioButton.setChecked(radioItem.isSelected);
        radioButton.setText(radioItem.name);
    }
}