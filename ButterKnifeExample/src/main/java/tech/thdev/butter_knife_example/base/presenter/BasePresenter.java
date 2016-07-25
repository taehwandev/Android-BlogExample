package tech.thdev.butter_knife_example.base.presenter;

import tech.thdev.butter_knife_example.base.presenter.view.BasePresenterView;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class BasePresenter<V extends BasePresenterView> {

    private V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    protected V getView() {
        return view;
    }
}
