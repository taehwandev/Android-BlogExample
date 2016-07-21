package tech.thdev.kotlin_example_01

import android.content.Context
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseRecyclerAdapter<T>(val context: Context) : RecyclerView.Adapter<BaseRecyclerView<*>>() {

    private var itemList: MutableList<T>? = null

    init {
        itemList = ArrayList<T>()
    }

    override fun onBindViewHolder(holder: BaseRecyclerView<*>?, position: Int) {
        holder?.onViewHolder(getItem(position), position)
    }

    protected fun getItem(position: Int): T? {
        return if (itemList != null) itemList!![position] else null
    }

    override fun getItemCount(): Int {
        return if (itemList != null) itemList!!.size else 0
    }

    protected fun getItemList(): MutableList<T> {
        return itemList
    }

    @JvmOverloads fun setItemList(itemList: MutableList<T>, isNotify: Boolean = true) {
        this.itemList = itemList

        if (isNotify) {
            notifyDataSetChanged()
        }
    }

    @JvmOverloads fun removeItem(position: Int, isNotify: Boolean = true): T {
        val item = getItem(position)
        if (itemList != null) {
            itemList!!.remove(item)
        }

        if (isNotify) {
            notifyItemRemoved(position)
        }
        return item
    }

    @JvmOverloads fun addItem(item: T, isNotify: Boolean = true) {
        if (itemList != null) {
            itemList!!.add(item)

            if (isNotify) {
                notifyItemChanged(item)
            }
        }
    }

    fun notifyItemChanged(item: T) {
        if (itemList != null) {
            val index = itemList!!.indexOf(item)
            notifyItemChanged(index)
        }
    }

    /**
     * @param itemList 의 데이터를 삭제하고 [android.support.v7.widget.RecyclerView.Adapter.notifyItemRangeRemoved] 함수 호출
     * *
     * @param position 아이템을 delete 해야할 위치
     */
    fun notifyItemRangeRemoved(itemList: List<T>?, position: Int) {
        if (itemList != null && itemList.size > 0) {
            getItemList().removeAll(itemList)
            notifyItemRangeRemoved(position, itemList.size)
        }
    }

    /**
     * @param itemList 의 데이터를 Insert [android.support.v7.widget.RecyclerView.Adapter.notifyItemRangeInserted] 함수 호출
     * *
     * @param position 아이템을 add 해야할 시작위치
     */
    fun notifyItemRangeInserted(itemList: List<T>?, position: Int) {
        if (itemList != null && itemList.size > 0) {
            getItemList().addAll(position, itemList)
            notifyItemRangeInserted(position, itemList.size)
        }
    }

    protected fun getItemIndex(item: T): Int {
        return if (itemList != null) itemList!!.indexOf(item) else -1
    }

    fun clear() {
        if (itemList != null) {
            itemList!!.clear()
        }
    }
}
