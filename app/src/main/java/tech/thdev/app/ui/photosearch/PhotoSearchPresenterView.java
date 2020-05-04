package tech.thdev.app.ui.photosearch;

import tech.thdev.app.base.presenter.BasePresenterView;
import tech.thdev.app.network.domain.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoSearchPresenterView extends BasePresenterView {

    void onRefresh();

    void onBottomSheetShow(Photo photo);
}

