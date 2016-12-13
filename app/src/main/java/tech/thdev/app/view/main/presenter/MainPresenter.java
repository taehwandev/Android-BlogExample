package tech.thdev.app.view.main.presenter;

import tech.thdev.app.adapter.pager.model.SectionsPagerModel;
import tech.thdev.base.presenter.AbstractPresenter;

/**
 * Created by tae-hwan on 12/11/16.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    private SectionsPagerModel sectionsPagerModel;

    @Override
    public void setSectionPagerModel(SectionsPagerModel sectionPagerModel) {
        this.sectionsPagerModel = sectionPagerModel;
    }

    @Override
    public void loadSectionPagerItem() {
        sectionsPagerModel.setListItem(1);
        sectionsPagerModel.setListItem(2);
        sectionsPagerModel.setListItem(3);

        getView().updatePager();
    }
}
