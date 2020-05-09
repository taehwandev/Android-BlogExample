package tech.thdev.app.ui.dailyboxoffice.presenter

import android.content.Context
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.thdev.app.BuildConfig
import tech.thdev.app.R
import tech.thdev.app.base.presenter.CommonPresenter
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.common.adapter.model.BoxOfficeAdapterContract

/**
 * Created by Tae-hwan on 12/12/2016.
 */
class DailyBoxOfficePresenter(
    private var boxOfficeModel: BoxOfficeAdapterContract.Model,
    private var boxOfficeView: BoxOfficeAdapterContract.View,
    private var movieSearchRepository: MovieSearchRepository
) : CommonPresenter<DailyBoxOfficeContract.View>(), DailyBoxOfficeContract.Presenter {

    override fun getDailyBoxOffice(targetDate: String) {
        if (BuildConfig.KOBIS_API_KEY.isEmpty()) {
            view?.showLoadFail(R.string.api_key_not_fount_error)
            return
        }
        if (boxOfficeModel.getItemCount() > 0) {
            return
        }

        movieSearchRepository.getDailyBoxOffice(targetDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view?.showProgress()
            }
            .doOnError {
                view?.hideProgress()
                view?.showLoadFail(R.string.daily_load_fail)
            }
            .subscribe({
                boxOfficeModel.addItem(
                    MovieChartItem(
                        it.boxOfficeResult.boxOfficeType,
                        "",
                        it.boxOfficeResult.showRange,
                        "",
                        "",
                        100
                    )
                )

                Flowable.fromIterable(it.boxOfficeResult.dailyBoxOfficeList)
                    .filter { it != null }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        view?.hideProgress()
                        boxOfficeView.reload()
                    }
                    .doOnError {
                        view?.showLoadFail(R.string.daily_load_fail)
                    }
                    .subscribe {
                        boxOfficeModel.addItem(
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
            }, {
                view?.hideProgress()
                view?.showLoadFail(R.string.daily_load_fail)
            })
    }
}