package tech.thdev.kotlin_example_01.view.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.network.FlickrModule
import tech.thdev.kotlin_example_01.util.setContentFragment
import tech.thdev.kotlin_example_01.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fragment: MainFragment = MainFragment()
        setContentFragment(R.id.frame_layout, fragment)

        MainPresenter(FlickrModule()).attachView(fragment)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
    }
}
