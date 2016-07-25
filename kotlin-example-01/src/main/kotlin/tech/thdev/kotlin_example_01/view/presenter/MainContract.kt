package tech.thdev.kotlin_example_01.view.presenter

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView
import tech.thdev.kotlin_example_01.view.adapter.model.PhotoDataModel

/**
 * Created by Tae-hwan on 7/21/16.
 */
interface MainContract {

    interface View: BaseView<Presenter> {

        fun showFailLoad()

        fun refresh()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter: BasePresenter<View> {

        fun setDataModel(model: PhotoDataModel)

        fun loadPhotos(page: Int)
    }
}