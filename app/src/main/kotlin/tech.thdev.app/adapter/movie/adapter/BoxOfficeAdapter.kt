package tech.thdev.app.adapter.movie.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.adapter.movie.adapter.holder.BoxOfficeHeaderViewHolder
import tech.thdev.app.adapter.movie.adapter.holder.BoxOfficeViewHolder
import tech.thdev.app.adapter.movie.adapter.model.BoxOfficeAdapterContract
import java.util.*

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class BoxOfficeAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BoxOfficeAdapterContract.Model, BoxOfficeAdapterContract.View {

    private val itemList = ArrayList<MovieChartItem>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        100 -> BoxOfficeHeaderViewHolder(context, parent)
        else -> BoxOfficeViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            100 -> {
                (holder as? BoxOfficeHeaderViewHolder)?.onBind(getItem(position), position)
            }
            else -> {
                (holder as? BoxOfficeViewHolder)?.onBind(getItem(position), position)
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