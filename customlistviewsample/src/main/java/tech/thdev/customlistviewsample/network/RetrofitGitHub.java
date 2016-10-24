package tech.thdev.customlistviewsample.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import tech.thdev.customlistviewsample.data.GitHubUserResponse;

/**
 * Created by tae-hwan on 10/25/16.
 */

public class RetrofitGitHub {

    private GitHubService gitHubService;

    private static RetrofitGitHub retrofitGitHub;

    public static RetrofitGitHub getInstance() {
        if (retrofitGitHub == null) {
            synchronized (RetrofitGitHub.class) {
                if (retrofitGitHub == null) {
                    retrofitGitHub = new RetrofitGitHub();
                }
            }
        }
        return retrofitGitHub;
    }

    private RetrofitGitHub() {
        Retrofit retrofit = RetrofitCreator.createRetrofit();
        gitHubService = retrofit.create(GitHubService.class);
    }

    /**
     * Retrofit Search url
     */
    public Call<GitHubUserResponse> searchGitHubUser(String userKeyword, int page, int perPage) {
        return gitHubService.searchUser(userKeyword, page, perPage);
    }
}
