package tech.thdev.app.view.dailyboxoffice.presenter

import android.content.Context
import tech.thdev.app.adapter.movie.adapter.model.BoxOfficeAdapterContract
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface DailyBoxOfficeContract {

    interface View : BaseView {

        fun isFinishing(): Boolean

        fun showLoadFail(message: String)
    }

    interface Presenter : BasePresenter<View> {
        var boxOfficeModel: BoxOfficeAdapterContract.Model
        var boxOfficeView: BoxOfficeAdapterContract.View

        var movieSearchRepository: MovieSearchRepository

        fun getDailyBoxOffice(context: Context, targetDate: String)
    }
}