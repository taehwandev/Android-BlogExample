package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 10/24/16.
 *
 * GitHub api [GitHub User search sample](https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000)
 */
class GitHubItem {
    @SerializedName("login")
    val login: String? = null
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
    @SerializedName("html_url")
    val htmlUrl: String? = null
    @SerializedName("score")
    val score: Float = 0f
}