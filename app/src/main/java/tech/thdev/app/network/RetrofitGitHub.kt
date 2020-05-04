package tech.thdev.app.network

import retrofit2.Call
import retrofit2.Retrofit
import tech.thdev.app.data.GitHubUserResponse

/**
 * Created by tae-hwan on 10/25/16.
 */
class RetrofitGitHub private constructor() {

    private val gitHubService: GitHubService

    init {
        val retrofit: Retrofit = createRetrofit()
        gitHubService = retrofit.create(GitHubService::class.java)
    }

    companion object {
        private var retrofitGitHub: RetrofitGitHub? = null
        fun newInstance(): RetrofitGitHub =
            retrofitGitHub ?: synchronized(RetrofitGitHub::class.java) {
                retrofitGitHub ?: RetrofitGitHub().also { retrofitGitHub = it }
            }
    }

    /**
     * Retrofit Search url
     */
    fun searchGitHubUser(
        userKeyword: String?,
        page: Int,
        perPage: Int
    ): Call<GitHubUserResponse> {
        return gitHubService.searchUser(userKeyword, page, perPage)
    }
}