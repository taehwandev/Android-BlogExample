package tech.thdev.hugo_example.adapter

import android.content.Context
import android.view.ViewGroup
import tech.thdev.base.adapter.BaseRecyclerAdapter
import tech.thdev.base.adapter.BaseRecyclerViewHolder
import tech.thdev.hugo_example.adapter.contract.ListAdapterContract
import tech.thdev.hugo_example.adapter.view.ListItemViewHolder
import tech.thdev.hugo_example.data.Items
import tech.thdev.hugo_example.listener.OnClickListener

/**
 * Created by rgpkorea on 06/10/2016.
 */
class ListAdapter(context: Context) : BaseRecyclerAdapter<Items>(context), ListAdapterContract.Model, ListAdapterContract.View {

    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerViewHolder<Items> {
        return ListItemViewHolder(this, parent)
    }

    override fun refresh() {
        notifyDataSetChanged()
    }
}