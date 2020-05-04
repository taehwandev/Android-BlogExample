package tech.thdev.app.ui.github.presenter

import tech.thdev.app.data.GitHubItem

/**
 * Created by tae-hwan on 10/25/16.
 */
interface GitHubUserContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun notifyListView()
        fun showFailLoad()
        fun addItem(item: GitHubItem)
        fun showChromeTabs(item: GitHubItem)
        fun getListItem(position: Int): GitHubItem
    }

    interface Presenter {
        fun loadGitHubUser(userKeyword: String?)
        fun onItemClick(position: Int)
    }
}