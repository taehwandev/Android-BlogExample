package tech.thdev.recycler_lambda_example.listener;

/**
 * Created by Tae-hwan on 4/12/16.
 */
public interface OnRecyclerItemLongClickListener<DATA> {

    boolean onItemLongClick(DATA item, int position);
}
