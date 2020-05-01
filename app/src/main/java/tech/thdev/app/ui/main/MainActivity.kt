package tech.thdev.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.R
import tech.thdev.app.databinding.ActivityMainBinding
import tech.thdev.app.ui.main.presenter.MainPresenter
import tech.thdev.app.util.replaceContentFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)

        val fragment = MainFragment.newInstance()
        val presenter = MainPresenter()
        presenter.attachView(fragment)

        replaceContentFragment(R.id.frame_layout, fragment)
    }
}