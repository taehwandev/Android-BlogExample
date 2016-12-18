package tech.thdev.app.view.presenter;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import tech.thdev.base.presenter.AbstractPresenter;

/**
 * Created by tae-hwan on 18/12/2016.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void startOverlayWindowService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            getView().showObtainingPermissionOverlayWindow();

        } else {
            getView().showStartOverlay();
        }
    }

    @Override
    public void onOverlayResult(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(context)) {
                getView().showStartOverlay();
            }
        }
    }
}
