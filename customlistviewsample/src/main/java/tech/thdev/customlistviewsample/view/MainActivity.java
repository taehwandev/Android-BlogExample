package tech.thdev.customlistviewsample.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import tech.thdev.customlistviewsample.R;
import tech.thdev.customlistviewsample.adapter.GitHubUserAdapter;
import tech.thdev.customlistviewsample.data.GitHubItem;
import tech.thdev.customlistviewsample.network.RetrofitGitHub;
import tech.thdev.customlistviewsample.view.presenter.MainContract;
import tech.thdev.customlistviewsample.view.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View, AbsListView.OnScrollListener {

    private ListView listView;
    private RelativeLayout rlProgressView;

    private GitHubUserAdapter gitHubUserAdapter;
    private MainPresenter presenter;

    private ArrayList<GitHubItem> gitHubItems = new ArrayList<>();

    private String searchKeyword = "tom+repos:>42";

    /**
     * Loading
     */
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        rlProgressView = (RelativeLayout) findViewById(R.id.rl_progress_view);
        rlProgressView.setVisibility(View.GONE);

        gitHubUserAdapter = new GitHubUserAdapter(this, 0, gitHubItems);
        listView.setAdapter(gitHubUserAdapter);
        listView.setOnScrollListener(this);

        presenter = new MainPresenter(RetrofitGitHub.getInstance(), this);
        presenter.loadGitHubUser(searchKeyword);
    }

    @Override
    public void showProgress() {
        isLoading = true;
        rlProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rlProgressView.setVisibility(View.GONE);
        isLoading = false;
    }

    @Override
    public void showFailLoad() {
        Toast.makeText(this, "GitHub User load fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addItem(GitHubItem item) {
        // GitHub User의 아이템 리스트에 add 한다
        gitHubItems.add(item);
    }

    @Override
    public void notifyListView() {
        // GitHub User adapter를 갱신한다
        gitHubUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isLoading && totalItemCount > 0) {
            if (firstVisibleItem + visibleItemCount >= (totalItemCount - 4)) {
                presenter.loadGitHubUser(searchKeyword);
            }
        }
    }
}
