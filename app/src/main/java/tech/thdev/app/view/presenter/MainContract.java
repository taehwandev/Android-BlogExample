package tech.thdev.app.view.presenter;

import android.content.Context;

import tech.thdev.base.presenter.BasePresenter;
import tech.thdev.base.presenter.BaseView;

/**
 * Created by tae-hwan on 18/12/2016.
 */

public interface MainContract {

    interface View extends BaseView {

        void showStartOverlay();

        void showObtainingPermissionOverlayWindow();
    }

    interface Presenter extends BasePresenter<View> {

        void startOverlayWindowService(Context context);

        void onOverlayResult(Context context);
    }
}
