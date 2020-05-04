package tech.thdev.app.ui.listview.presenter

import tech.thdev.app.base.presenter.CommonPresenter
import tech.thdev.app.data.GitHubItem
import tech.thdev.app.data.source.GitHubUserDataSource
import tech.thdev.app.data.source.GitHubUserRepository

/**
 * Created by tae-hwan on 10/30/16.
 */
class CustomListPresenter(
    private val gitHubUserRepository: GitHubUserRepository
) : CommonPresenter<CustomListContract.View>(), CustomListContract.Presenter {

    companion object {
        private const val DEFAULT_ITEM_COUNT = 50
    }

    private var page: Int = 0

    private var isLastItem = false

    override fun loadGitHubUser(userKeyword: String) {
        if (!isLastItem) {
            view?.showProgress()
            gitHubUserRepository.getGitHubUserData(
                userKeyword,
                ++page,
                DEFAULT_ITEM_COUNT,
                gitHubUserCallback
            )
        }
    }

    private val gitHubUserCallback = object : GitHubUserDataSource.LoadGitHubUserDataCallback {

        override fun onLoaded(list: List<GitHubItem>) {
            list.forEach {
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