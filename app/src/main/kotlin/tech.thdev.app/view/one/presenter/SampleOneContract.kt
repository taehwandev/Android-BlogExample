package tech.thdev.app.view.one.presenter

import tech.thdev.app.view.one.adapter.model.SampleOneAdapterContract
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by Tae-hwan on 12/12/2016.
 */

interface SampleOneContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
        var sampleOneOneModel: SampleOneAdapterContract.Model
        var sampleOneOneView: SampleOneAdapterContract.View
    }
}