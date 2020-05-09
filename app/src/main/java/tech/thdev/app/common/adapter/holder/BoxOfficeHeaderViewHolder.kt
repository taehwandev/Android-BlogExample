package tech.thdev.app.common.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.R
import tech.thdev.app.data.MovieChartItem

/**
 * Created by tae-hwan on 12/12/2016.
 */
class BoxOfficeHeaderViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_box_office_header_view, parent, false)
) {
    private val tvTitle by lazy {
        itemView.findViewById<TextView>(R.id.tv_title)
    }
    private val tvDate by lazy {
        itemView.findViewById<TextView>(R.id.tv_date)
    }

    fun onBind(item: MovieChartItem) {
        tvTitle.text = item.title
        tvDate.text = item.date
    }
}