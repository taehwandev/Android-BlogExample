package tech.thdev.app.view.presenter

import android.content.Context
import android.os.Build
import android.provider.Settings

/**
 * Created by tae-hwan on 18/12/2016.
 */
class MainPresenter : MainContract.Presenter {

    override lateinit var view: MainContract.View

    override fun startOverlayWindowService(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && !Settings.canDrawOverlays(context)
        ) {
            view.showObtainingPermissionOverlayWindow()
        } else {
            view.showStartOverlay()
        }
    }

    override fun onOverlayResult(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(context)) {
                view.showStartOverlay()
            }
        }
    }
}