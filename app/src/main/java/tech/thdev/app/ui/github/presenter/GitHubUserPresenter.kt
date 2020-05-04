package tech.thdev.app.ui.github.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.thdev.app.data.GitHubUserResponse
import tech.thdev.app.network.RetrofitGitHub

/**
 * Created by tae-hwan on 10/25/16.
 */
class GitHubUserPresenter(
    private val retrofitGitHub: RetrofitGitHub,
    private val view: GitHubUserContract.View
) : GitHubUserContract.Presenter {

    private var page = 0
    private var isLastItem = false
    override fun loadGitHubUser(userKeyword: String?) {
        if (page > 0 && isLastItem) {
            return
        }
        view.showProgress()
        val gitHubUserCall: Call<GitHubUserResponse> = retrofitGitHub.searchGitHubUser(
            userKeyword,
            ++page,
            DEFAULT_ITEM_COUNT
        )
        gitHubUserCall.enqueue(object : Callback<GitHubUserResponse?> {
            override fun onResponse(
                call: Call<GitHubUserResponse?>,
                response: Response<GitHubUserResponse?>
            ) {
                if (!response.isSuccessful) {
                    view.hideProgress()

                    /*
                     * API rate limit exceeded for IP Address.
                     * (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)
                     */isLastItem = true
                    return
                }

                // Retrofit에서 GSON을 GitHubUserReponse로 변환한 결과를 받아온다
                response.body()?.let { item ->
                    item.items?.forEach {
                        view.addItem(it)
                    }
                }

                view.hideProgress()
                view.notifyListView()
            }

            override fun onFailure(
                call: Call<GitHubUserResponse?>,
                t: Throwable
            ) {
                view.hideProgress()
                view.showFailLoad()
            }
        })
    }

    override fun onItemClick(position: Int) {
        // GitHubItem arrayList에서 아이템을 가져오고, 이를 View에 show 하도록 전달한다
        view.showChromeTabs(view.getListItem(position))
    }

    companion object {
        private const val DEFAULT_ITEM_COUNT = 50
    }
}