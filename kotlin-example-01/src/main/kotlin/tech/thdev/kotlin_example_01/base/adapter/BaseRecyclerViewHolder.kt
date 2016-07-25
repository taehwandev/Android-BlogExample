package tech.thdev.kotlin_example_01.base.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.kotlin_example_01.base.model.BaseItem

/**
 * Created by Tae-hwan on 7/21/16.
 */
abstract class BaseRecyclerViewHolder<ITEM: BaseItem>(protected val adapter: BaseRecyclerAdapter<ITEM>, itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup?, adapter: BaseRecyclerAdapter<ITEM>)
        : this(adapter, LayoutInflater.from(adapter.context).inflate(layoutRes, parent, false)) {
    }

    abstract fun onViewHolder(item: ITEM?, position: Int)

    protected val context: Context
        get() = adapter.context
}