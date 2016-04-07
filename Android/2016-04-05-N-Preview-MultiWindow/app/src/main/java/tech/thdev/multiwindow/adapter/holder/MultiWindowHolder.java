package tech.thdev.multiwindow.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tech.thdev.multiwindow.R;

/**
 * Created by taehwankwon on 4/4/16.
 */
public class MultiWindowHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text)
    TextView textView;

    public MultiWindowHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public MultiWindowHolder(Context context, ViewGroup viewGroup) {
        this(LayoutInflater.from(context).inflate(R.layout.item_multi_window, viewGroup, false));
    }

    public void onBindViewHolder(String item, int position) {
        textView.setText(item);
    }
}
