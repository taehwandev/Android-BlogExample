package tech.thdev.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import tech.thdev.R

abstract class BaseActivity(
    @LayoutRes private val layoutRes: Int,
    @StringRes private val titleRes: Int
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        setToolbar()
    }

    private fun setToolbar() {
        findViewById<Toolbar>(R.id.toolbar).run {
            setTitle(titleRes)
            setSupportActionBar(this)
        }
    }
}