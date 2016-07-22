package tech.thdev.kotlin_example_01.view.adapter

import android.content.Context
import android.view.ViewGroup
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerViewHolder
import tech.thdev.kotlin_example_01.model.Photo
import tech.thdev.kotlin_example_01.view.adapter.model.PhotoDataModel
import tech.thdev.kotlin_example_01.view.adapter.view.PhotoViewHolder

/**
 * Created by Tae-hwan on 7/22/16.
 */
class PhotoAdapter(context: Context): BaseRecyclerAdapter<Photo>(context), PhotoDataModel {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerViewHolder<Photo> {
        return PhotoViewHolder(parent, this)
    }
}