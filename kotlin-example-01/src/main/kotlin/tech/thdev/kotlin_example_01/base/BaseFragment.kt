package tech.thdev.kotlin_example_01.base

import android.support.v4.app.Fragment
import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseFragment<P: BasePresenter<*>> : Fragment(), BaseView<P> {

    private var presenter: P? = null

    override fun setPresenter(presenter: P) {
        this.presenter = presenter
    }

    fun presenter(): P? = presenter as P?

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
        presenter = null
    }
}
