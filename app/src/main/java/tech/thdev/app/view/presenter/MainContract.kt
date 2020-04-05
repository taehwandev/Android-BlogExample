package tech.thdev.app.view.presenter

import android.content.Context

/**
 * Created by tae-hwan on 18/12/2016.
 */
interface MainContract {
    interface View {
        fun showStartOverlay()
        fun showObtainingPermissionOverlayWindow()
    }

    interface Presenter {
        var view: MainContract.View

        fun startOverlayWindowService(context: Context?)
        fun onOverlayResult(context: Context?)
    }
}