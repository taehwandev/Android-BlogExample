package tech.thdev.app.common.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.app.R
import tech.thdev.app.data.MovieChartItem

/**
 * Created by Tae-hwan on 12/12/2016.
 */
class BoxOfficeViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_box_office_view, parent, false)
) {

    private val tvRank by lazy {
        itemView.findViewById<TextView>(R.id.tv_rank)
    }
    private val tvTitle by lazy {
        itemView.findViewById<TextView>(R.id.tv_title)
    }
    private val tvAttendance by lazy {
        itemView.findViewById<TextView>(R.id.tv_attendance)
    }
    private val imgRankInten by lazy {
        itemView.findViewById<ImageView>(R.id.img_rank_inten)
    }

    fun onBind(item: MovieChartItem) {
        tvRank.text = item.rank
        tvTitle.text = item.title
        tvAttendance.text = String.format("관객수 : %,d 명", item.attendance.toLong())

        val rankInten = item.changeRanking.toInt()
        when {
            rankInten < 0 ->
                imgRankInten.setImageResource(R.drawable.ic_down)
            rankInten > 0 ->
                imgRankInten.setImageResource(R.drawable.ic_up)
            else ->
                imgRankInten.setImageResource(R.drawable.ic_none)
        }
    }
}