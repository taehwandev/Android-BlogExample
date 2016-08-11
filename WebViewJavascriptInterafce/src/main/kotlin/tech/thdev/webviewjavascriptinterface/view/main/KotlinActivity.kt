package tech.thdev.webviewjavascriptinterface.view.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import tech.thdev.webviewjavascriptinterface.R
import tech.thdev.webviewjavascriptinterface.base.view.BaseActivity
import tech.thdev.webviewjavascriptinterface.constant.Constant
import tech.thdev.webviewjavascriptinterface.util.replaceContentFragment

/**
 * Created by Tae-hwan on 8/11/16.
 */
class KotlinActivity: BaseActivity() {

    private val toolBar by lazy {
        findViewById(R.id.toolbar) as Toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        initToolbar()

        val url = intent.getStringExtra(Constant.KEY_INTENT_URL)
        val fragment = KotlinFragment(url)
        replaceContentFragment(R.id.frame_layout, fragment)
    }

    private fun initToolbar() {
        setSupportActionBar(toolBar)
    }
}