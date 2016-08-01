package tech.thdev.kotlin_example_01.listener

import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter

/**
 * Created by Tae-hwan on 8/1/16.
 */
interface ItemClickListener {

    fun OnClickListener(baseRecyclerAdapter: BaseRecyclerAdapter<*>, position: Int)
}