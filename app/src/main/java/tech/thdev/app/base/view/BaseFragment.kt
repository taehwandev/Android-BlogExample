package tech.thdev.app.base.view

import androidx.fragment.app.Fragment
import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView


/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseFragment<P : BasePresenter<*>> : Fragment(), BaseView<P> {

    protected var presenter: P? = null
        private set

    override fun onPresenter(presenter: P) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
    }
}
