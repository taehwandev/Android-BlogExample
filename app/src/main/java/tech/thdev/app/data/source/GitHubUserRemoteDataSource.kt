package tech.thdev.app.data.source

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.thdev.app.data.GitHubUserResponse
import tech.thdev.app.network.GitHubService
import tech.thdev.app.network.createRetrofit

/**
 * Created by tae-hwan on 10/30/16.
 */

class GitHubUserRemoteDataSource : GitHubUserDataSource {

    private val gitHubService: GitHubService = createRetrofit().create(GitHubService::class.java)

    override fun getGitHubUserData(
        userKeyword: String,
        page: Int,
        perPage: Int,
        callback: GitHubUserDataSource.LoadGitHubUserDataCallback
    ) {
        gitHubService.searchUser(userKeyword, page, perPage)
            .enqueue(object : Callback<GitHubUserResponse> {
                override fun onResponse(
                    call: Call<GitHubUserResponse>,
                    response: Response<GitHubUserResponse>
                ) {
                    if (!response.isSuccessful) {
                        callback.onDataNotAvailable()
                        return
                    }

                    response.body()?.let {
                        callback.onLoaded(it.items ?: mutableListOf())
                    }
                }

                override fun onFailure(call: Call<GitHubUserResponse>, t: Throwable) {
                    callback.onDataLoadFail()
                }
            })
    }
}