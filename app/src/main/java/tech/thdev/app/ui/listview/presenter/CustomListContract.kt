package tech.thdev.app.ui.listview.presenter

import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView
import tech.thdev.app.data.GitHubItem

/**
 * Created by tae-hwan on 10/30/16.
 */
interface CustomListContract {

    interface View : BaseView {

        fun showProgress()

        fun hideProgress()

        fun notifyListView()

        fun showFailLoad()

        fun addItem(item: GitHubItem)

        fun showChromeTabs(item: GitHubItem)

        fun getListItem(position: Int): GitHubItem

        fun showErrorMessage(s: String)
    }

    interface Presenter : BasePresenter<View> {

        fun loadGitHubUser(userKeyword: String)

        fun onItemClick(position: Int)
    }
}