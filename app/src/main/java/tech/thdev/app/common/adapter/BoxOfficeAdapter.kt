package tech.thdev.app.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.common.adapter.holder.BoxOfficeHeaderViewHolder
import tech.thdev.app.common.adapter.holder.BoxOfficeViewHolder
import tech.thdev.app.common.adapter.model.BoxOfficeAdapterContract

/**
 * Created by Tae-hwan on 12/12/2016.
 */
class BoxOfficeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BoxOfficeAdapterContract.Model, BoxOfficeAdapterContract.View {

    companion object {
        const val VIEW_TYPE_HEADER = 100
    }

    private val itemList = ArrayList<MovieChartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_HEADER -> BoxOfficeHeaderViewHolder(
                parent
            )
            else -> BoxOfficeViewHolder(
                parent
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HEADER -> {
                (holder as? BoxOfficeHeaderViewHolder)?.onBind(getItem(position))
            }
            else -> {
                (holder as? BoxOfficeViewHolder)?.onBind(getItem(position))
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