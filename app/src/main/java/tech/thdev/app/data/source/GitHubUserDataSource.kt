package tech.thdev.app.data.source

import tech.thdev.app.data.GitHubItem

/**
 * Created by tae-hwan on 10/30/16.
 */

interface GitHubUserDataSource {

    interface LoadGitHubUserDataCallback {
        fun onLoaded(list: List<GitHubItem>)

        fun onDataNotAvailable()

        fun onDataLoadFail()
    }

    fun getGitHubUserData(
        userKeyword: String,
        page: Int,
        perPage: Int,
        callback: GitHubUserDataSource.LoadGitHubUserDataCallback
    )
}