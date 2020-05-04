package tech.thdev.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by tae-hwan on 10/24/16.
 */
class GitHubUserResponse {
    @SerializedName("message")
    val message: String? = null
    @SerializedName("documentation_url")
    val documentationUrl: String? = null
    @SerializedName("total_count")
    val totalCount: String? = null
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false
    @SerializedName("items")
    val items: List<GitHubItem>? = null
}