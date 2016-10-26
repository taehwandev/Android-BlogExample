package tech.thdev.hugo_example.view.paresenter;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import tech.thdev.base.presenter.AbstractPresenter;
import tech.thdev.hugo_example.adapter.contract.ListAdapterContract;
import tech.thdev.hugo_example.data.Items;
import tech.thdev.hugo_example.view.MainContract;

/**
 * Created by rgpkorea on 06/10/2016.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    private ListAdapterContract.Model listModel;
    private ListAdapterContract.View listView;

    @Override
    public void setListModel(ListAdapterContract.Model model) {
        this.listModel = model;
    }

    @Override
    public void setListView(ListAdapterContract.View listView) {
        this.listView = listView;
    }

    @Override
    public void getNumber() {
        Observable.range(1, 10)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        listView.refresh();
                        getView().updateNumber();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        listModel.addItem(new Items("Item : " + integer, 0));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void showItem(int position) {
        Items items = listModel.getItem(position);
        getView().showItemsView(items);
    }
}
