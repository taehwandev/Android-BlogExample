package tech.thdev.customlistviewsample.view.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.thdev.customlistviewsample.data.GitHubItem;
import tech.thdev.customlistviewsample.data.GitHubUserResponse;
import tech.thdev.customlistviewsample.network.RetrofitGitHub;

/**
 * Created by tae-hwan on 10/25/16.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final int DEFAULT_ITEM_COUNT = 50;

    private RetrofitGitHub retrofitGitHub;
    private MainContract.View view;

    private int page = 0;

    private boolean isLastItem = false;

    public MainPresenter(RetrofitGitHub retrofitGitHub, MainContract.View view) {
        this.retrofitGitHub = retrofitGitHub;
        this.view = view;
    }

    @Override
    public void loadGitHubUser(String userKeyword) {
        if (page > 0 && isLastItem) {
            return;
        }

        view.showProgress();
        final Call<GitHubUserResponse> gitHubUserCall = retrofitGitHub.searchGitHubUser(userKeyword, ++page, DEFAULT_ITEM_COUNT);
        gitHubUserCall.enqueue(new Callback<GitHubUserResponse>() {
            @Override
            public void onResponse(Call<GitHubUserResponse> call, Response<GitHubUserResponse> response) {
                if (!response.isSuccessful()) {
                    view.hideProgress();

                    /*
                     * API rate limit exceeded for IP Address.
                     * (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)
                     */
                    isLastItem = true;
                    return;
                }

                // Retrofit에서 GSON을 GitHubUserReponse로 변환한 결과를 받아온다
                GitHubUserResponse gitHubUserResponse = response.body();
                if (gitHubUserResponse != null) {
                    if (gitHubUserResponse.items != null && gitHubUserResponse.items.size() > 0) {
                        // items를 추가한다
                        for (GitHubItem item : gitHubUserResponse.items) {
                            view.addItem(item);
                        }
                    }
                }

                view.hideProgress();
                view.notifyListView();
            }

            @Override
            public void onFailure(Call<GitHubUserResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailLoad();
            }
        });
    }
}
