package tech.thdev.app.view.dailyboxoffice.presenter

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.thdev.app.BuildConfig
import tech.thdev.app.R
import tech.thdev.app.adapter.movie.adapter.model.BoxOfficeAdapterContract
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class DailyBoxOfficePresenter : AbstractPresenter<DailyBoxOfficeContract.View>(), DailyBoxOfficeContract.Presenter {

    lateinit override var boxOfficeModel: BoxOfficeAdapterContract.Model

    lateinit override var boxOfficeView: BoxOfficeAdapterContract.View

    lateinit override var movieSearchRepository: MovieSearchRepository

    override fun getDailyBoxOffice(context: Context, targetDate: String) {
        if (BuildConfig.KOBIS_API_KEY.isEmpty()) {
            if (!(view?.isFinishing() ?: false)) {
                view?.showLoadFail(context.getString(R.string.api_key_not_fount_error))
            }
            return
        }
        movieSearchRepository.getDailyBoxOffice(targetDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    if (!(view?.isFinishing() ?: false)) {
                        view?.showLoadFail(context.getString(R.string.daily_load_fail))
                    }
                }
                .subscribe({
                    boxOfficeModel.addItem(MovieChartItem(it.boxOfficeResult.boxofficeType, "", it.boxOfficeResult.showRange, "", "", 100))

                    Flowable.fromIterable(it.boxOfficeResult.dailyBoxOfficeList)
                            .filter { it != null }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                if (!(view?.isFinishing() ?: false)) {
                                    boxOfficeView.reload()
                                }
                            }
                            .doOnError {
                                if (!(view?.isFinishing() ?: false)) {
                                    view?.showLoadFail(context.getString(R.string.daily_load_fail))
                                }
                            }
                            .subscribe {
                                boxOfficeModel.addItem(MovieChartItem(it.movieNm, it.rank, it.openDt, it.audiAcc, it.rankInten, 0))
                            }
                }, {
                    if (!(view?.isFinishing() ?: false)) {
                        view?.showLoadFail(context.getString(R.string.daily_load_fail))
                    }
                })
    }
}