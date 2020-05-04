package tech.thdev.app.ui.listview

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import tech.thdev.app.R
import tech.thdev.app.base.ui.BasePresenterActivity
import tech.thdev.app.data.GitHubItem
import tech.thdev.app.data.source.GitHubUserRepository
import tech.thdev.app.ui.listview.presenter.CustomListContract
import tech.thdev.app.ui.listview.presenter.CustomListPresenter

class CustomListActivity :
    BasePresenterActivity<CustomListContract.View, CustomListContract.Presenter>(),
    CustomListContract.View {

    private val gitHubItems = ArrayList<GitHubItem>()

    private val adapter: CustomListAdapter by lazy {
        CustomListAdapter(this, 0, gitHubItems)
    }

    private val searchKeyword = "tom+repos:>42"

    private var isLoading: Boolean = false

    private val listView by lazy {
        findViewById<ListView>(R.id.list_view)
    }
    private val progressView by lazy {
        findViewById<ConstraintLayout>(R.id.rl_progress_view)
    }

    override fun onCreatePresenter() = CustomListPresenter(GitHubUserRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.run {
            adapter = this@CustomListActivity.adapter
            setOnItemClickListener { _, _, position, _ ->
                presenter.onItemClick(position)
            }
            setOnScrollListener(object : AbsListView.OnScrollListener {
                override fun onScroll(
                    view: AbsListView?,
                    firstVisibleItem: Int,
                    visibleItemCount: Int,
                    totalItemCount: Int
                ) {
                    if (!isLoading && totalItemCount > 0) {
                        if (firstVisibleItem + visibleItemCount >= totalItemCount - 4) {
                            presenter.loadGitHubUser(searchKeyword)
                        }
                    }
                }

                override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

                }
            })
        }

        presenter.loadGitHubUser(searchKeyword)
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
        isLoading = true
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
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
        val customTabsIntent = CustomTabsIntent.Builder().apply {
            // Changes the background color for the omnibox. colorInt is an int
            // that specifies a Color.
            setToolbarColor(resources.getColor(R.color.colorPrimary))
            setStartAnimations(this@CustomListActivity, 0, 0)
            setExitAnimations(this@CustomListActivity, 0, 0)
        }.build()

        customTabsIntent.launchUrl(this, Uri.parse(item.htmlUrl))
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
}
