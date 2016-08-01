package tech.thdev.kotlin_example_01.view.main.adapter.view

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerViewHolder
import tech.thdev.kotlin_example_01.data.Photo
import tech.thdev.kotlin_example_01.listener.ItemClickListener
import tech.thdev.kotlin_example_01.listener.LongClickListener

/**
 * Created by Tae-hwan on 7/22/16.
 */
class PhotoViewHolder(parent: ViewGroup?,
                      adapter: BaseRecyclerAdapter<Photo>,
                      val longClickListener: LongClickListener?,
                      val itemClickListener: ItemClickListener?) : BaseRecyclerViewHolder<Photo>(R.layout.item_photo, parent, adapter) {

    private val imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.image_view) as ImageView
    }

    override fun onViewHolder(item: Photo?, position: Int) {
        Glide.with(context)
                .load(item?.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .centerCrop()
                .crossFade()
                .into(imageView)

        itemView.setOnLongClickListener {
            longClickListener?.onLongClickListener(adapter, position)
            false
        }

        itemView.setOnClickListener {
            itemClickListener?.OnClickListener(adapter, position)
        }
    }
}