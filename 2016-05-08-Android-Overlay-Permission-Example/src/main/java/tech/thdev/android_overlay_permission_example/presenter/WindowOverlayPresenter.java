package tech.thdev.android_overlay_permission_example.presenter;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import tech.thdev.android_overlay_permission_example.base.presenter.BasePresenter;
import tech.thdev.android_overlay_permission_example.presenter.view.WindowOverlayView;

/**
 * Created by tae-hwan on 5/8/16.
 */
public class WindowOverlayPresenter extends BasePresenter<WindowOverlayView> {

    public WindowOverlayPresenter(WindowOverlayView view) {
        super(view);
    }

    public void startOverlayWindowService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            getView().onObtainingPermissionOverlayWindow();

        } else {
            getView().onStartOverlay();
        }
    }

    public void onOverlayResult(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(context)) {
                getView().onStartOverlay();
            }
        }
    }
}
