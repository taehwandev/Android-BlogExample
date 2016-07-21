package tech.thdev.kotlin_example_01

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class Abstract<VIEW> : BasePresenter<VIEW> {

    private var view: VIEW? = null

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected val isAttachedView: Boolean
        get() = view != null
}
