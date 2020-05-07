package tech.thdev.app.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.R
import tech.thdev.app.data.BottomData

/**
 * Created by Tae-hwan on 09/12/2016.
 */
class RecyclerBottomSheetViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_recycler_bottom_sheet, parent, false)
) {

    private val imgIcon: ImageView by lazy {
        itemView.findViewById<ImageView>(R.id.img_icon)
    }

    private val tvTitle: TextView by lazy {
        itemView.findViewById<TextView>(R.id.tv_title)
    }

    fun onBind(item: BottomData) {
        imgIcon.setImageResource(item.iconRes)
        tvTitle.text = item.title
    }
}