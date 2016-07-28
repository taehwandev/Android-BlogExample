package tech.thdev.kotlin_example_01.view.adapter.view

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerAdapter
import tech.thdev.kotlin_example_01.base.adapter.BaseRecyclerViewHolder
import tech.thdev.kotlin_example_01.data.Photo

/**
 * Created by Tae-hwan on 7/22/16.
 */
class PhotoViewHolder(parent: ViewGroup?, adapter: BaseRecyclerAdapter<Photo>) : BaseRecyclerViewHolder<Photo>(R.layout.item_photo, parent, adapter) {

    private val imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.image_view) as ImageView
    }

    override fun onViewHolder(item: Photo?, position: Int) {
        Glide.with(context)
                .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", item?.farm, item?.server, item?.id, item?.secret))
                .placeholder(android.R.drawable.ic_menu_gallery)
                .centerCrop()
                .crossFade()
                .into(imageView)

        itemView.setOnLongClickListener {
            true
        }
    }
}