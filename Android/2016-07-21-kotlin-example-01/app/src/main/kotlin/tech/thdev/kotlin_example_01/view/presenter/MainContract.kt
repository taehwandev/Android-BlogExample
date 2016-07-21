package tech.thdev.kotlin_example_01.view.presenter

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter
import tech.thdev.kotlin_example_01.base.presenter.BaseView

/**
 * Created by Tae-hwan on 7/21/16.
 */
interface MainContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter<View> {

    }
}