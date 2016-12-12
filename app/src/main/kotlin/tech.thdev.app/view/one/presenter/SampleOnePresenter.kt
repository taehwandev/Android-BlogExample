package tech.thdev.app.view.one.presenter

import tech.thdev.app.view.one.adapter.model.SampleOneAdapterContract
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by Tae-hwan on 12/12/2016.
 */

class SampleOnePresenter : AbstractPresenter<SampleOneContract.View>(), SampleOneContract.Presenter {
    lateinit override var sampleOneOneModel: SampleOneAdapterContract.Model

    lateinit override var sampleOneOneView: SampleOneAdapterContract.View
}