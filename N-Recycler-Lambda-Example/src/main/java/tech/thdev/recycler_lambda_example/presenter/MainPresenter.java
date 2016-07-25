package tech.thdev.recycler_lambda_example.presenter;

import android.support.annotation.DrawableRes;

/**
 * Created by Tae-hwan on 4/14/16.
 */
public interface MainPresenter {

    void onImageItemLongClick(@DrawableRes int resource);

    interface View {

        void showFlickerImageBottomSheet(@DrawableRes int resource);
    }
}
