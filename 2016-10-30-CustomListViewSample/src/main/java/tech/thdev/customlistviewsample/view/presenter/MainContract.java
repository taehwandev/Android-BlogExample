package tech.thdev.customlistviewsample.view.presenter;

import tech.thdev.customlistviewsample.data.GitHubItem;

/**
 * Created by tae-hwan on 10/25/16.
 */

public interface MainContract {

    interface View {

        void showProgress();

        void hideProgress();

        void notifyListView();

        void showFailLoad();

        void addItem(GitHubItem item);

        void showChromeTabs(GitHubItem item);

        GitHubItem getListItem(int position);
    }

    interface Presenter {

        void loadGitHubUser(String userKeyword);

        void onItemClick(int position);
    }
}
