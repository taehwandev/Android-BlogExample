package tech.thdev.hugo_example.view;

import tech.thdev.base.presenter.BasePresenter;
import tech.thdev.base.presenter.BaseView;
import tech.thdev.hugo_example.adapter.contract.ListAdapterContract;
import tech.thdev.hugo_example.data.Items;

/**
 * Created by rgpkorea on 06/10/2016.
 */

public interface MainContract {

    interface View extends BaseView {

        void updateNumber();

        void showItemsView(Items items);
    }

    interface Presenter extends BasePresenter<View> {

        void setListModel(ListAdapterContract.Model model);

        void setListView(ListAdapterContract.View listView);

        void getNumber();

        void showItem(int position);
    }
}
