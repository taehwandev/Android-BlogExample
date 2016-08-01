package tech.thdev.kotlin_example_01.view.main.adapter

import android.content.Context
import android.view.ViewGroup
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerViewHolder
import tech.thdev.kotlin_example_01.data.Photo
import tech.thdev.kotlin_example_01.listener.ItemClickListener
import tech.thdev.kotlin_example_01.listener.LongClickListener
import tech.thdev.kotlin_example_01.view.main.adapter.model.PhotoDataModel
import tech.thdev.kotlin_example_01.view.main.adapter.view.PhotoViewHolder

/**
 * Created by Tae-hwan on 7/22/16.
 */
class PhotoAdapter(context: Context) : BaseRecyclerAdapter<Photo>(context), PhotoDataModel {

    var longClickListener: LongClickListener? = null

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerViewHolder<Photo>
            = PhotoViewHolder(parent, this, longClickListener, itemClickListener)
}