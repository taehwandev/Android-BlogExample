package tech.thdev.hugo_example.adapter.view

import android.view.ViewGroup
import android.widget.TextView
import tech.thdev.base.adapter.BaseRecyclerAdapter
import tech.thdev.base.adapter.BaseRecyclerViewHolder
import tech.thdev.hugo_example.R
import tech.thdev.hugo_example.adapter.ListAdapter
import tech.thdev.hugo_example.data.Items

/**
 * Created by rgpkorea on 06/10/2016.
 */
class ListItemViewHolder(adapter: BaseRecyclerAdapter<Items>, parent: ViewGroup?)
    : BaseRecyclerViewHolder<Items>(R.layout.item_holder, parent, adapter) {

    private val textView by lazy {
        itemView?.findViewById(R.id.text) as TextView
    }

    override fun onViewHolder(item: Items?, position: Int) {
        textView.text = item?.number ?: "Null item"

        itemView?.setOnClickListener {
            getAdapter()?.onClickListener?.onItemClick(getAdapter(), position)
        }
    }

    private fun getAdapter() : ListAdapter? {
        return adapter as? ListAdapter
    }
}