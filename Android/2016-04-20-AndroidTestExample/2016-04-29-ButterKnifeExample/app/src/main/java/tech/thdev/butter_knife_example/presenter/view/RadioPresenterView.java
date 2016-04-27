package tech.thdev.butter_knife_example.presenter.view;

import tech.thdev.butter_knife_example.base.presenter.view.BasePresenterView;
import tech.thdev.butter_knife_example.bean.RadioItem;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public interface RadioPresenterView extends BasePresenterView {

    void showBottomSheet(RadioItem item);
}
