package tech.thdev.customlistviewsample.view.presenter

import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView
import tech.thdev.customlistviewsample.data.GitHubItem
import tech.thdev.customlistviewsample.data.source.GitHubUserRepository

/**
 * Created by tae-hwan on 10/30/16.
 */

interface CustomListViewContract {

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

        var gitHubUserRepository: GitHubUserRepository?

        fun loadGitHubUser(userKeyword: String)

        fun onItemClick(position: Int)
    }
}