package tech.thdev.app.ui.radio;

import tech.thdev.app.base.presenter.BasePresenterView;
import tech.thdev.app.data.RadioItem;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public interface RadioPresenterView extends BasePresenterView {

    void notifyDataSetChanged();
    void showBottomSheet(RadioItem item);
}
