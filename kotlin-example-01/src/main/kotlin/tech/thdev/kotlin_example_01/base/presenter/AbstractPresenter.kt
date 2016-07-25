package tech.thdev.kotlin_example_01.base.presenter

/**
 * Created by Tae-hwan on 7/22/16.
 */
abstract class AbstractPresenter<VIEW : BaseView<*>> : BasePresenter<VIEW> {

    private var view: VIEW? = null

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getView(): VIEW? = view
}