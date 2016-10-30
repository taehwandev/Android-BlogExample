package tech.thdev.customlistviewsample.data.source

import tech.thdev.customlistviewsample.data.GitHubItem
import java.util.*

/**
 * Created by tae-hwan on 10/30/16.
 */

interface GitHubUserDataSource {

    interface LoadGitHubUserDataCallback {
        fun onLoaded(list: ArrayList<GitHubItem>?)

        fun onDataNotAvailable()

        fun onDataLoadFail()
    }

    fun getGitHubUserData(userKeyword: String, page: Int, perPage: Int, callback: LoadGitHubUserDataCallback?)
}