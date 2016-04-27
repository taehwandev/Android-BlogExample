package tech.thdev.butter_knife_example.presenter;

import tech.thdev.butter_knife_example.adapter.model.RadioDataModel;
import tech.thdev.butter_knife_example.base.presenter.BasePresenter;
import tech.thdev.butter_knife_example.bean.RadioItem;
import tech.thdev.butter_knife_example.presenter.view.RadioPresenterView;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public class RadioPresenter extends BasePresenter<RadioPresenterView> {

    private RadioDataModel radioDataModel;

    public RadioPresenter(RadioPresenterView view, RadioDataModel radioDataModel) {
        super(view);

        this.radioDataModel = radioDataModel;
    }

    public void onRecyclerItemClick(int position) {
        RadioItem radioItem = radioDataModel.getRadioItem(position);

        getView().showBottomSheet(radioItem);
    }

    public void removeRadioItem(int position) {
        radioDataModel.remove(position);
    }

    /**
     * Item add
     */
    public void addItem(String name) {
        radioDataModel.add(new RadioItem(name));
    }
}
