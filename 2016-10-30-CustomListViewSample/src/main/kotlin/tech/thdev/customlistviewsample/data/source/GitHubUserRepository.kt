package tech.thdev.customlistviewsample.data.source

/**
 * Created by tae-hwan on 10/30/16.
 */

object GitHubUserRepository : GitHubUserDataSource {

    private val remoteData: GitHubUserRemoteDataSource

    init {
        remoteData = GitHubUserRemoteDataSource()
    }

    override fun getGitHubUserData(userKeyword: String, page: Int, perPage: Int, callback: GitHubUserDataSource.LoadGitHubUserDataCallback?) {
        remoteData.getGitHubUserData(userKeyword, page, perPage, callback)
    }
}