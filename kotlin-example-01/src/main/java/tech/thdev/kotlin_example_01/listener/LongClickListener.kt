package tech.thdev.kotlin_example_01.listener

import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter

/**
 * Created by Tae-hwan on 7/29/16.
 */
interface LongClickListener {

    fun onLongClickListener(baseRecyclerAdapter: BaseRecyclerAdapter<*>, position: Int): Boolean
}