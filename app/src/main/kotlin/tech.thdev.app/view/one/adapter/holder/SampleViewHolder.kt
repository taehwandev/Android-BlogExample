package tech.thdev.app.view.one.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_sample_view.view.*
import tech.thdev.app.R

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleViewHolder(val context: Context, parent: ViewGroup?)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sample_view, parent, false)) {

    fun onBind(item: String, position: Int) {
        with(itemView) {
            tv_title.text = item
        }
    }
}