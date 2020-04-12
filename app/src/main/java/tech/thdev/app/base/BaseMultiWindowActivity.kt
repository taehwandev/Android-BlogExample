package tech.thdev.app.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_multi_window.*
import kotlinx.android.synthetic.main.item_multi_window.view.*
import tech.thdev.R
import tech.thdev.simpleadapter.SimpleRecyclerViewAdapter
import tech.thdev.simpleadapter.bindViewHolder

abstract class BaseMultiWindowActivity(
    @LayoutRes layoutRes: Int,
    @StringRes titleRes: Int
) : BaseActivity(layoutRes, titleRes) {

    private val simpleAdapter: SimpleRecyclerViewAdapter by lazy {
        SimpleRecyclerViewAdapter {
            bindViewHolder<String>(R.layout.item_multi_window) {
                tv_message.text = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_view.run {
            layoutManager = LinearLayoutManager(this@BaseMultiWindowActivity)
            this.adapter = simpleAdapter
        }

        addItem("onCreate() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onStart() {
        super.onStart()
        addItem("onStart() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onResume() {
        super.onResume()
        addItem("onResume() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onPause() {
        super.onPause()
        addItem("onPause() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onRestart() {
        super.onRestart()
        addItem("onRestart() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onStop() {
        super.onStop()
        addItem("onStop() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onDestroy() {
        super.onDestroy()
        addItem("onDestroy() isInMultiWindowMode $isInMultiWindowMode")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        addItem("onConfigurationChanged() isInMultiWindowMode $isInMultiWindowMode, newConfig ${newConfig.orientation}")
    }

    private fun addItem(message: String) {
        android.util.Log.d("TEMP", message)
        simpleAdapter.addItem(0, message)
        val newPosition = simpleAdapter.itemCount - 1
        simpleAdapter.notifyItemChanged(newPosition)
        recycler_view.scrollToPosition(newPosition)
    }
}