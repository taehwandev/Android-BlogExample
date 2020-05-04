package tech.thdev.app.base.ui

import android.os.Bundle
import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView

abstract class BasePresenterActivity<in VIEW: BaseView, PRESENTER : BasePresenter<VIEW>> : BaseActivity(), BaseView {

    protected lateinit var presenter: PRESENTER
        private set

    abstract fun onCreatePresenter(): PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter.attachView(this as VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}