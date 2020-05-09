package tech.thdev.app.ui.dailyboxoffice.presenter

import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView

/**
 * Created by Tae-hwan on 12/12/2016.
 */
interface DailyBoxOfficeContract {

    interface View : BaseView {

        fun showLoadFail(messageId: Int)

        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BasePresenter<View> {
        fun getDailyBoxOffice(targetDate: String)
    }
}