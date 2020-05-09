package tech.thdev.app.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tech.thdev.app.R
import tech.thdev.app.ui.basic.PlaceholderFragment
import tech.thdev.app.ui.dailyboxoffice.DailyBoxOfficeFragment
import tech.thdev.app.ui.weekboxoffice.WeeklyBoxOfficeFragment

class MainActivity : AppCompatActivity() {

    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initView()
    }

    private val fragmentMap = mutableMapOf<Int, Fragment>()

    private fun initView() {
        fragmentMap[R.id.action_one] = PlaceholderFragment.newInstance()
        fragmentMap[R.id.action_two] = DailyBoxOfficeFragment.newInstance()
        fragmentMap[R.id.action_three] = WeeklyBoxOfficeFragment.newInstance()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            fragmentMap[it.itemId]?.let { fragment ->
                replaceFragment(fragment)
                true
            } ?: false
        }
        bottomNavigationView.selectedItemId = R.id.action_one
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return if (item.itemId == R.id.action_settings) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}