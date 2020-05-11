package tech.thdev.app.ui.login.presenter

import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView

/**
 * Created by tae-hwan on 2/17/17.
 */
interface LoginContract {

    interface View : BaseView {

        fun showProgress()
        fun hideProgress()

        fun emailEmptyError()
        fun passwordInvalid()
        fun emailInvalid()
        fun loginFail()

        fun successLogin(email: String)
        fun successLogout()
    }

    interface Presenter : BasePresenter<View> {

        fun loginUser(email: String, password: String)

        fun logout()

        fun clear()
    }
}