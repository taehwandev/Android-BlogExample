package tech.thdev.app.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tech.thdev.app.data.GitHubUserResponse

/**
 * Created by tae-hwan on 10/25/16.
 */
interface GitHubService {
    // https://api.github.com/search/users?q=tom+repos:%3E42&page=3&per_page=50
    @GET("/search/users?")
    fun searchUser(
        @Query(value = "q", encoded = true) userKeyword: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<GitHubUserResponse>
}