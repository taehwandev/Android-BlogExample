package tech.thdev.app.ui.weekboxoffice.presenter

import android.content.Context
import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView

/**
 * Created by tae-hwan on 12/13/16.
 */
interface WeeklyBoxOfficeContract {
    interface View : BaseView {
        fun loadFail(messageRes: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun getWeeklyBoxOffice(
            targetDate: String
        )
    }
}