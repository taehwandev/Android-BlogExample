package tech.thdev.webviewjavascriptinterface.base.view

import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

/**
 * Created by Tae-hwan on 8/8/16.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        ButterKnife.bind(this@BaseActivity)
    }
}