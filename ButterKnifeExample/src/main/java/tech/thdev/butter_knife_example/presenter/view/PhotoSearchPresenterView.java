package tech.thdev.butter_knife_example.presenter.view;

import tech.thdev.butter_knife_example.base.presenter.view.BasePresenterView;
import tech.thdev.butter_knife_example.network.domain.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoSearchPresenterView extends BasePresenterView {

    void onRefresh();

    void onBottomSheetShow(Photo photo);
}

