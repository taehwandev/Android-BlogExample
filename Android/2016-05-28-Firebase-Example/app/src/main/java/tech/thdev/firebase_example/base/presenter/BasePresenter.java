package tech.thdev.firebase_example.base.presenter;

/**
 * Created by Tae-hwan on 5/27/16.
 */

public abstract class BasePresenter<V extends BaseView> implements IBasePresenter {

    private V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
