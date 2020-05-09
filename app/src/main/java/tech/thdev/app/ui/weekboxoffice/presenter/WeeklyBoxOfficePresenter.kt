package tech.thdev.app.ui.weekboxoffice.presenter

import android.text.TextUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.thdev.app.BuildConfig
import tech.thdev.app.R
import tech.thdev.app.base.presenter.CommonPresenter
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.common.adapter.BoxOfficeAdapter
import tech.thdev.app.common.adapter.model.BoxOfficeAdapterContract

/**
 * Created by tae-hwan on 12/13/16.
 */
class WeeklyBoxOfficePresenter(
    private val adapterModel: BoxOfficeAdapterContract.Model,
    private val adapterView: BoxOfficeAdapterContract.View,
    private val movieSearchRepository: MovieSearchRepository
) : CommonPresenter<WeeklyBoxOfficeContract.View>(),
    WeeklyBoxOfficeContract.Presenter {

    override fun getWeeklyBoxOffice(
        targetDate: String
    ) {
        if (TextUtils.isEmpty(BuildConfig.KOBIS_API_KEY)) {
            view?.loadFail(R.string.api_key_not_fount_error)
            return
        }

        if (adapterModel.getItemCount() > 0) {
            return
        }

        movieSearchRepository.getWeekBoxOffice(targetDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                view?.loadFail(R.string.weekly_load_fail)
            }
            .subscribe({
                adapterModel.addItem(
                    MovieChartItem(
                        it.boxOfficeResult.boxOfficeType,
                        "",
                        it.boxOfficeResult.showRange,
                        "",
                        "",
                        BoxOfficeAdapter.VIEW_TYPE_HEADER
                    )
                )
                Flowable.fromIterable(it.boxOfficeResult.weeklyBoxOfficeList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        adapterView.reload()
                    }
                    .doOnError {
                        view?.loadFail(R.string.weekly_load_fail)
                    }
                    .subscribe {
                        adapterModel.addItem(
                            MovieChartItem(
                                it.movieNm,
                                it.rank,
                                it.openDt,
                                it.audiAcc,
                                it.rankInten,
                                0
                            )
                        )
                    }
            },
                {
                    view?.loadFail(R.string.weekly_load_fail)
                })
    }
}