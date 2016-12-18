package tech.thdev.app_kotlin.view.presenter

import android.content.Context
import android.os.Build
import android.provider.Settings.canDrawOverlays
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by tae-hwan on 18/12/2016.
 */

class MainPresenter : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    override fun startOverlayWindowService(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !canDrawOverlays(context)) {
            view?.showObtainingPermissionOverlayWindow()

        } else {
            view?.showStartOverlay()
        }
    }

    override fun onOverlayResult(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (canDrawOverlays(context)) {
                view?.showStartOverlay()
            }
        }
    }
}