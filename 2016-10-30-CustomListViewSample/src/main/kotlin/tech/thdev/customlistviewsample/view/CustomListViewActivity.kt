package tech.thdev.customlistviewsample.view

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.base.view.BasePresenterActivity
import tech.thdev.customlistviewsample.R
import tech.thdev.customlistviewsample.adapter.CustomListAdapter
import tech.thdev.customlistviewsample.data.GitHubItem
import tech.thdev.customlistviewsample.data.source.GitHubUserRepository
import tech.thdev.customlistviewsample.view.presenter.CustomListViewContract
import tech.thdev.customlistviewsample.view.presenter.CustomListViewPresenter
import java.util.*

class CustomListViewActivity : BasePresenterActivity<CustomListViewContract.View, CustomListViewContract.Presenter>(), CustomListViewContract.View, AbsListView.OnScrollListener {

    private var adapter: CustomListAdapter? = null

    private val gitHubItems = ArrayList<GitHubItem>()

    private val searchKeyword = "tom+repos:>42"

    private var isLoading: Boolean = false


    override fun onCreatePresenter() = CustomListViewPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomListAdapter(this, 0, gitHubItems)

        presenter?.gitHubUserRepository = GitHubUserRepository

        presenter?.loadGitHubUser(searchKeyword)

        list_view.adapter = adapter
        list_view.setOnItemClickListener { adapterView, view, i, l -> presenter?.onItemClick(i) }
        list_view.setOnScrollListener(this)
    }

    override fun showProgress() {
        rl_progress_view.visibility = View.VISIBLE
        isLoading = true
    }

    override fun hideProgress() {
        rl_progress_view.visibility = View.GONE
        isLoading = false
    }

    override fun notifyListView() {
        adapter?.notifyDataSetChanged()
    }

    override fun showFailLoad() {
        Toast.makeText(this, "Load Fail", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun addItem(item: GitHubItem) {
        gitHubItems.add(item)
    }

    override fun showChromeTabs(item: GitHubItem) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        val builder = CustomTabsIntent.Builder()
        // Changes the background color for the omnibox. colorInt is an int
        // that specifies a Color.
        builder.setToolbarColor(resources.getColor(R.color.colorPrimary))

        builder.setStartAnimations(this, 0, 0)
        builder.setExitAnimations(this, 0, 0)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.html_url))
    }

    override fun getListItem(position: Int) = gitHubItems[position]

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_page_two, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.action_change_sample -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (!isLoading && totalItemCount > 0) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount - 4) {
                presenter?.loadGitHubUser(searchKeyword)
            }
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

    }
}
