package tech.thdev.app.ui.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import tech.thdev.app.R
import tech.thdev.app.data.GitHubItem
import tech.thdev.app.databinding.ActivityMainBinding
import tech.thdev.app.network.RetrofitGitHub
import tech.thdev.app.ui.github.presenter.GitHubUserContract
import tech.thdev.app.ui.github.presenter.GitHubUserPresenter
import tech.thdev.app.ui.listview.CustomListActivity
import java.util.*

class GitHubUserActivity : AppCompatActivity(), GitHubUserContract.View {

    companion object {
        private const val SEARCH_KEYWORD = "tom+repos:>42"
    }

    private val gitHubItems: ArrayList<GitHubItem> = ArrayList<GitHubItem>()

    private val presenter: GitHubUserContract.Presenter by lazy {
        GitHubUserPresenter(RetrofitGitHub.newInstance(), this)
    }

    private val gitHubUserAdapter: GitHubUserAdapter by lazy {
        GitHubUserAdapter(this, 0, gitHubItems)
    }

    /**
     * Loading
     */
    private var isLoading = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.run {
            adapter = gitHubUserAdapter
            setOnScrollListener(object : AbsListView.OnScrollListener {
                override fun onScroll(
                    view: AbsListView?,
                    firstVisibleItem: Int,
                    visibleItemCount: Int,
                    totalItemCount: Int
                ) {
                    if (!isLoading && totalItemCount > 0) {
                        if (firstVisibleItem + visibleItemCount >= totalItemCount - 4) {
                            presenter.loadGitHubUser(SEARCH_KEYWORD)
                        }
                    }
                }

                override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

                }
            })
            setOnItemClickListener { _, _, position, _ ->
                presenter.onItemClick(position)
            }
        }
        presenter.loadGitHubUser(SEARCH_KEYWORD)
    }

    override fun showProgress() {
        if (!isLoading) {
            binding.rlProgressView.visibility = View.VISIBLE
        }
        isLoading = true
    }

    override fun hideProgress() {
        if (isLoading) {
            binding.rlProgressView.visibility = View.GONE
        }
        isLoading = false
    }

    override fun showFailLoad() {
        Toast.makeText(this, "GitHub User load fail", Toast.LENGTH_SHORT).show()
    }

    override fun addItem(item: GitHubItem) {
        // GitHub User의 아이템 리스트에 add 한다
        gitHubItems.add(item)
    }

    override fun getListItem(position: Int): GitHubItem {
        // GitHub의 User 아이템을 return 한다
        return gitHubItems[position]
    }

    override fun notifyListView() {
        // GitHub User adapter를 갱신한다
        gitHubUserAdapter.notifyDataSetChanged()
    }

    override fun showChromeTabs(item: GitHubItem) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
        // and launch the desired Url with CustomTabsIntent.launchUrl()
        val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().apply {
            // Changes the background color for the omnibox. colorInt is an int
            // that specifies a Color.
            setToolbarColor(resources.getColor(R.color.colorPrimary))
            setStartAnimations(this@GitHubUserActivity, 0, 0)
            setExitAnimations(this@GitHubUserActivity, 0, 0)
        }.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.htmlUrl))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_page_one, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_change_sample -> {
                startActivity(Intent(this, CustomListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}