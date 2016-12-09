package tech.thdev.app.view.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.thdev.app.data.BottomData;

/**
 * Created by Tae-hwan on 09/12/2016.
 */

public class RecyclerBottomSheetAdapter extends RecyclerView.Adapter<RecyclerBottomSheetViewHolder> {

    private ArrayList<BottomData> itemList = new ArrayList<>();

    private Context context;

    public RecyclerBottomSheetAdapter(Context context) {
        this.context = context;
    }

    public void setItem(BottomData item) {
        itemList.add(item);
    }

    public BottomData getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public RecyclerBottomSheetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerBottomSheetViewHolder(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerBottomSheetViewHolder holder, int position) {
        holder.onBind(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
