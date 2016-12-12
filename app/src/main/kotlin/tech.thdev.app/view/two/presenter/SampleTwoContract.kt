package tech.thdev.app.view.two.presenter

import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface SampleTwoContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}