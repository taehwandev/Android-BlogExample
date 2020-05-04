package tech.thdev.app.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import tech.thdev.app.R
import tech.thdev.app.base.view.BaseActivity
import tech.thdev.app.databinding.ActivityMainBinding
import tech.thdev.app.databinding.AppBarMainBinding
import tech.thdev.app.databinding.ContentMainBinding
import tech.thdev.app.ui.main.presenter.MainPresenter
import tech.thdev.app.util.replaceContentFragment


class MainActivity : BaseActivity() {

    private val fragment: MainFragment by lazy {
        MainFragment.newInstance()
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarMainBinding: AppBarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appBarMainBinding = AppBarMainBinding.inflate(layoutInflater)
        val contentMainBinding = ContentMainBinding.inflate(layoutInflater)

        setSupportActionBar(appBarMainBinding.toolbar)
        initView()
    }

    override fun onResume() {
        super.onResume()
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        Log.i(
            "TAG",
            "display width : " + metrics.widthPixels + ", height : " + metrics.heightPixels + ", densityDpi : " + metrics.densityDpi
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(
            "TAG",
            "config : " + newConfig.orientation + ", newConfig.screenLayout : " + newConfig.screenLayout
        )

        Log.e("TAG", "newConfig.densityDpi : " + newConfig.densityDpi)
        Log.i("TAg", "uiMode : " + newConfig.uiMode)
        Log.i("TAg", "smallestScreenWidthDp : " + newConfig.smallestScreenWidthDp)
        Log.i(
            "TAg",
            "screenWidthDp : " + newConfig.screenWidthDp + ", screenHeightDp : " + newConfig.screenHeightDp
        )
    }

    private fun initView() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            appBarMainBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            // Handle navigation view item clicks here.
            when (it.itemId) {
                R.id.nav_manage -> {

                }
                R.id.nav_share -> {
                    fragment.urlShare()
                }
                R.id.bookmarks_google, R.id.bookmarks_stackoverflow, R.id.bookmarks_thdev -> {
                    fragment.loadUrl(it.titleCondensed.toString())
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        replaceContentFragment(R.id.frame_layout, fragment)

        // Presenter create ...
        MainPresenter().attachView(fragment)
    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            fragment.canGoBack() -> {
                fragment.goBack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}