package tech.thdev.multiwindow.presenter;

import tech.thdev.multiwindow.adapter.model.MultiWindowDataModel;
import tech.thdev.multiwindow.base.presenter.BasePresenter;
import tech.thdev.multiwindow.presenter.view.MultiWindowPresenterView;

/**
 * Created by Tae-hwan on 5/2/16.
 */
public class MultiWindowPresenter extends BasePresenter<MultiWindowPresenterView> {

    private MultiWindowDataModel dataModel;

    public MultiWindowPresenter(MultiWindowPresenterView view, MultiWindowDataModel dataModel) {
        super(view);

        this.dataModel = dataModel;
    }

    public void addItem(String name) {
        dataModel.add(name);

        getView().onItemAdded(dataModel.getSize());
    }
}
