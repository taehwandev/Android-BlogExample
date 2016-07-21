package tech.thdev.kotlin_example_01.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by Tae-hwan on 7/21/16.
 */
abstract class BaseRecyclerAdapter<ITEM>(val context: Context) : RecyclerView.Adapter<BaseRecyclerViewHolder<ITEM>>() {

    private val itemList: MutableList<ITEM> = ArrayList()

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<ITEM>?, position: Int) {
        holder?.onViewHolder(getItem(position), position)
    }

    fun getItem(position: Int): ITEM? = itemList[position]

    fun removeItem(position: Int) {
        itemList.removeAt(position)
    }

    fun addItem(item: ITEM) {
        itemList.add(item)
    }
}