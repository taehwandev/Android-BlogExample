package tech.thdev.app.adapter.movie.adapter.model

import tech.thdev.app.data.MovieChartItem

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface BoxOfficeAdapterContract {

    interface View {
        fun reload()
    }

    interface Model {

        fun addItem(item: MovieChartItem)

        fun getItem(position: Int): MovieChartItem

        fun getItemCount(): Int

        fun getItems(): List<MovieChartItem>
    }
}