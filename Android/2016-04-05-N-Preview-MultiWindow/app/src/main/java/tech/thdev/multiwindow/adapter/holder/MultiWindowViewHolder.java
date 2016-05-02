package tech.thdev.multiwindow.adapter.holder;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import tech.thdev.multiwindow.R;
import tech.thdev.multiwindow.adapter.MultiWindowAdapter;
import tech.thdev.multiwindow.base.adapter.view.BaseRecyclerViewHolder;

/**
 * Created by taehwankwon on 4/4/16.
 */
public class MultiWindowViewHolder extends BaseRecyclerViewHolder<MultiWindowAdapter, String> {

    @BindView(R.id.text)
    TextView textView;

    public MultiWindowViewHolder(ViewGroup parent, MultiWindowAdapter adapter) {
        super(R.layout.item_multi_window, parent, adapter);
    }

    @Override
    public void onViewHolder(@Nullable String item, int position) {
        if (item != null) {
            textView.setText(item);
        }
    }
}
