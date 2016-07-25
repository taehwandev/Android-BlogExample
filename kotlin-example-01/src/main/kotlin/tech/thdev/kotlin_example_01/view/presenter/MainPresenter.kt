package tech.thdev.kotlin_example_01.view.presenter

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import tech.thdev.kotlin_example_01.base.presenter.AbstractPresenter
import tech.thdev.kotlin_example_01.network.RetrofitFlicker
import tech.thdev.kotlin_example_01.view.adapter.model.PhotoDataModel

/**
 * Created by Tae-hwan on 7/21/16.
 */
class MainPresenter(val retrofitFlicker: RetrofitFlicker) : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    private var model: PhotoDataModel? = null

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        getView()?.setPresenter(this)
    }

    override fun setDataModel(model: PhotoDataModel) {
        this.model = model
    }

    override fun loadPhotos(page: Int) {
        val photos = retrofitFlicker.getRecentPhotos(page)
        photos
                .subscribeOn(Schedulers.io())
                .map { it.photos }
                .filter { it != null && it.photo.size > 0 }
                .filter { it != null && it.page != it.pages }
                .flatMap { Observable.from(it.photo) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    getView()?.showProgress()
                }
                .doOnUnsubscribe {
                    getView()?.refresh()
                    getView()?.hideProgress()
                }
                .subscribe(
                        { model?.addItem(it) },
                        { getView()?.showFailLoad() })
    }
}