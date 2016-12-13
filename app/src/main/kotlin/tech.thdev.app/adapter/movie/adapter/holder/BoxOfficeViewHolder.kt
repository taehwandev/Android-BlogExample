package tech.thdev.app.adapter.movie.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_box_office_view.view.*
import tech.thdev.app.R
import tech.thdev.app.data.MovieChartItem

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class BoxOfficeViewHolder(val context: Context, parent: ViewGroup?)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_box_office_view, parent, false)) {

    fun onBind(item: MovieChartItem, position: Int) {
        with(itemView) {
            tv_rank.text = item.rank
            tv_title.text = item.title
            tv_attendance.text = String.format("관객수 : %,d 명", item.attendance.toLong())

            val rankInten = item.changeRanking.toInt()
            if (rankInten < 0) {
                img_rank_inten.setImageResource(R.drawable.ic_down)

            } else if (rankInten > 0) {
                img_rank_inten.setImageResource(R.drawable.ic_up)

            } else {
                img_rank_inten.setImageResource(R.drawable.ic_none)
            }
        }
    }
}