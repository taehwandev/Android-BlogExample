package tech.thdev.kotlin_example_01.base.view

import android.support.v4.app.Fragment
import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseFragment<P : BasePresenter<*>>() : Fragment(), BaseView<P> {

    protected var presenter: P? = null
        private set

    override fun onPresenter(presenter: P) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter!!.detachView()
        presenter = null
    }
}
