package tech.thdev.kotlin_example_01.view.flickr.presenter

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView
import tech.thdev.kotlin_example_01.view.flickr.adapter.model.PhotoDataModel

/**
 * Created by Tae-hwan on 7/21/16.
 */
interface MainContract {

    interface View: BaseView<Presenter> {

        fun showFailLoad()

        fun refresh()

        fun showProgress()

        fun hideProgress()

        fun initPhotoList()

        fun showBlurDialog(imageUrl: String?)
    }

    interface Presenter: BasePresenter<View> {

        /**
         * Adapter setting.
         */
        fun setDataModel(model: PhotoDataModel)

        /**
         * Recent Photos
         */
        fun loadPhotos(page: Int)

        /**
         * keyword search photos
         */
        fun searchPhotos(page: Int, safeSearch: Int, text: String?)

        /**
         * Long click item
         */
        fun updateLongClickItem(position: Int)

        /**
         * Subscribe destroy.
         */
        fun unSubscribeSearch()
    }
}