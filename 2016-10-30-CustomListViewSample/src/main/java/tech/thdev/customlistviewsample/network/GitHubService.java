package tech.thdev.customlistviewsample.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.thdev.customlistviewsample.data.GitHubUserResponse;

/**
 * Created by tae-hwan on 10/25/16.
 */

public interface GitHubService {

    // https://api.github.com/search/users?q=tom+repos:%3E42&page=3&per_page=50
    @GET("/search/users?")
    Call<GitHubUserResponse> searchUser(
            @Query(value = "q", encoded=true) String userKeyword,
            @Query("page") int page,
            @Query("per_page") int perPage);
}
