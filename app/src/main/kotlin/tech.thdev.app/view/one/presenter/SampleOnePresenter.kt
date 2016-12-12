package tech.thdev.app.view.one.presenter

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.thdev.app.data.MovieChartItem
import tech.thdev.app.data.source.MovieSearchRepository
import tech.thdev.app.view.one.adapter.model.SampleOneAdapterContract
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleOnePresenter : AbstractPresenter<SampleOneContract.View>(), SampleOneContract.Presenter {

    lateinit override var sampleOneOneModel: SampleOneAdapterContract.Model

    lateinit override var sampleOneOneView: SampleOneAdapterContract.View

    lateinit override var movieSearchRepository: MovieSearchRepository

    override fun searchMovie(targetDate: String) {
        movieSearchRepository.getBoxOffice(targetDate)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    sampleOneOneModel.addItem(MovieChartItem(it.boxOfficeResult.boxofficeType, "", it.boxOfficeResult.showRange, "", 100))

                    Flowable.fromIterable(it.boxOfficeResult.dailyBoxOfficeList)
                            .filter { it != null }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                view?.isFinishing()?.run {
                                    sampleOneOneView.reload()
                                }
                            }
                            .subscribe {
                                sampleOneOneModel.addItem(MovieChartItem(it.movieNm, it.rank, it.openDt, it.rankInten, 0))
                            }
                }
    }
}