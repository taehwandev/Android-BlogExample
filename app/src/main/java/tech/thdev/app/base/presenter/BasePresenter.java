package tech.thdev.app.base.presenter;

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
