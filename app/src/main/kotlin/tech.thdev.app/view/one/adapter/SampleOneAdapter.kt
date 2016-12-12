package tech.thdev.app.view.one.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.view.one.adapter.holder.SampleViewHeaderViewHolder
import tech.thdev.app.view.one.adapter.holder.SampleViewHolder
import tech.thdev.app.view.one.adapter.model.SampleOneAdapterContract
import java.util.*

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleOneAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), SampleOneAdapterContract.Model, SampleOneAdapterContract.View {

    private val itemList = ArrayList<MovieChartItem>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        100 -> SampleViewHeaderViewHolder(context, parent)
        else -> SampleViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            100 -> {
                (holder as? SampleViewHeaderViewHolder)?.onBind(getItem(position), position)
            }
            else -> {
                (holder as? SampleViewHolder)?.onBind(getItem(position), position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun getItemCount() = itemList.size

    override fun addItem(item: MovieChartItem) {
        itemList.add(item)
    }

    override fun getItem(position: Int): MovieChartItem {
        return itemList[position]
    }

    override fun getItems(): List<MovieChartItem> {
        return itemList
    }

    override fun reload() {
        notifyDataSetChanged()
    }
}