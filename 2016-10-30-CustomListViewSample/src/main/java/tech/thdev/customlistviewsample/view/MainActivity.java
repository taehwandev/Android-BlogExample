package tech.thdev.customlistviewsample.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity implements MainContract.View, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private RelativeLayout rlProgressView;

    private GitHubUserAdapter gitHubUserAdapter;
    private MainContract.Presenter presenter;

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
        listView.setOnItemClickListener(this);

        presenter = new MainPresenter(RetrofitGitHub.getInstance(), this);
        presenter.loadGitHubUser(searchKeyword);
    }

    @Override
    public void showProgress() {
        if (!isLoading) {
            rlProgressView.setVisibility(View.VISIBLE);
        }
        isLoading = true;
    }

    @Override
    public void hideProgress() {
        if (isLoading) {
            rlProgressView.setVisibility(View.GONE);
        }
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
    public GitHubItem getListItem(int position) {
        // GitHub의 User 아이템을 return 한다
        return gitHubItems.get(position);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    @Override
    public void showChromeTabs(GitHubItem item) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // Changes the background color for the omnibox. colorInt is an int
        // that specifies a Color.
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));

        builder.setStartAnimations(this, 0, 0);
        builder.setExitAnimations(this, 0, 0);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.html_url));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_sample) {
            startActivity(new Intent(this, CustomListViewActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
