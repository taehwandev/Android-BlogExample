package tech.thdev.app.view.main.presenter;

import tech.thdev.app.adapter.pager.model.SectionsPagerModel;
import tech.thdev.base.presenter.BasePresenter;
import tech.thdev.base.presenter.BaseView;

/**
 * Created by tae-hwan on 12/11/16.
 */

public interface MainContract {

    interface View extends BaseView {

        void updatePager();
    }

    interface Presenter extends BasePresenter<View> {

        void setSectionPagerModel(SectionsPagerModel sectionPagerModel);

        void loadSectionPagerItem();
    }
}
