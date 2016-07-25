package tech.thdev.android_overlay_permission_example.base.presenter;

import tech.thdev.android_overlay_permission_example.base.presenter.view.BaseView;

/**
 * Created by tae-hwan on 5/8/16.
 */
public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    protected T getView() {
        return view;
    }
}
