package tech.thdev.kotlin_example_01

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseRecyclerView<T>(protected val adapter: BaseRecyclerAdapter<*>, itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup, adapter: BaseRecyclerAdapter<*>) : this(adapter, LayoutInflater.from(adapter.getContext()).inflate(layoutRes, parent, false)) {
    }

    abstract fun onViewHolder(item: T?, position: Int)

    protected val context: Context
        get() = adapter.context
}
