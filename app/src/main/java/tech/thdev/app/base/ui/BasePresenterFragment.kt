package tech.thdev.app.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView

abstract class BasePresenterFragment<in VIEW : BaseView, PRESENTER : BasePresenter<VIEW>> : BaseFragment(), BaseView {

    protected lateinit var presenter: PRESENTER
        private set

    abstract fun onCreatePresenter(): PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = onCreatePresenter()
        presenter.attachView(this as VIEW)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}