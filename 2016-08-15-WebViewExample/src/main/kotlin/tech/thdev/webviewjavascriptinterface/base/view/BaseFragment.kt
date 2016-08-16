package tech.thdev.kotlin_example_01.base.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView

/**
 * Created by Tae-hwan on 7/21/16.
 */

abstract class BaseFragment<P : BasePresenter<*>>() : Fragment(), BaseView<P> {

    protected var presenter: P? = null
        private set

    override fun onPresenter(presenter: P?) {
        this.presenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let {
            ButterKnife.bind(this@BaseFragment, it)
        }
    }

    abstract fun getLayout(): Int @LayoutRes

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
    }
}
