package tech.thdev.android_overlay_permission_example.presenter.view;

import tech.thdev.android_overlay_permission_example.base.presenter.view.BaseView;

/**
 * Created by tae-hwan on 5/8/16.
 */
public interface WindowOverlayView extends BaseView {

    void onStartOverlay();

    void onObtainingPermissionOverlayWindow();
}
