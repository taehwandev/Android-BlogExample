package tech.thdev.app.data.source

/**
 * Created by tae-hwan on 10/30/16.
 */

class GitHubUserRepository : GitHubUserDataSource {

    private val remoteData: GitHubUserRemoteDataSource = GitHubUserRemoteDataSource()

    override fun getGitHubUserData(
        userKeyword: String,
        page: Int,
        perPage: Int,
        callback: GitHubUserDataSource.LoadGitHubUserDataCallback
    ) {
        remoteData.getGitHubUserData(userKeyword, page, perPage, callback)
    }
}