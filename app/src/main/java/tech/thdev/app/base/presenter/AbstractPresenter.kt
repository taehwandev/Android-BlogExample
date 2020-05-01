package tech.thdev.app.base.presenter

abstract class AbstractPresenter<VIEW : BaseView<*>> : BasePresenter<VIEW> {

    var view: VIEW? = null
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun isAttachView() = view != null
}