package tech.thdev.customlistviewsample.view.presenter

import tech.thdev.base.presenter.AbstractPresenter
import tech.thdev.customlistviewsample.data.GitHubItem
import tech.thdev.customlistviewsample.data.source.GitHubUserDataSource
import tech.thdev.customlistviewsample.data.source.GitHubUserRepository
import java.util.*

/**
 * Created by tae-hwan on 10/30/16.
 */

class CustomListViewPresenter : AbstractPresenter<CustomListViewContract.View>(), CustomListViewContract.Presenter {

    override var gitHubUserRepository: GitHubUserRepository? = null

    private val DEFAULT_ITEM_COUNT = 50

    private var page: Int = 0

    private var isLastItem = false

    override fun loadGitHubUser(userKeyword: String) {
        if (!isLastItem) {
            view?.showProgress()
            gitHubUserRepository?.getGitHubUserData(userKeyword, ++page, DEFAULT_ITEM_COUNT, gitHubUserCallback)
        }
    }

    private val gitHubUserCallback = object : GitHubUserDataSource.LoadGitHubUserDataCallback {

        override fun onLoaded(list: ArrayList<GitHubItem>?) {
            list?.forEach {
                view?.addItem(it)
            }
            view?.notifyListView()
            view?.hideProgress()
        }

        override fun onDataNotAvailable() {
            isLastItem = true
            view?.showErrorMessage("But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.")
            view?.hideProgress()
        }

        override fun onDataLoadFail() {
            view?.showFailLoad()
            view?.hideProgress()
        }
    }

    override fun onItemClick(position: Int) {
        view?.getListItem(position)?.let {
            view?.showChromeTabs(it)
        }
    }
}