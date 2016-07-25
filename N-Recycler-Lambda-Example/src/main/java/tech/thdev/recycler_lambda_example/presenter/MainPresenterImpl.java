package tech.thdev.recycler_lambda_example.presenter;

import android.support.annotation.DrawableRes;

/**
 * Created by Tae-hwan on 4/14/16.
 */
public class MainPresenterImpl implements MainPresenter {


    private MainPresenter.View view;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
    }

    @Override
    public void onImageItemLongClick(@DrawableRes int resource) {
        view.showFlickerImageBottomSheet(resource);
    }
}
