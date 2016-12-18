package tech.thdev.app_kotlin.view.presenter

import android.content.Context
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by tae-hwan on 18/12/2016.
 */

interface MainContract {

    interface View : BaseView {

        fun showStartOverlay()

        fun showObtainingPermissionOverlayWindow()
    }

    interface Presenter : BasePresenter<View> {

        fun startOverlayWindowService(context: Context)

        fun onOverlayResult(context: Context)
    }
}