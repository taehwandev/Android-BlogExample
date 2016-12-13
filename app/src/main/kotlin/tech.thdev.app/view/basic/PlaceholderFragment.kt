package tech.thdev.app.view.basic

import android.os.Bundle
import android.view.View
import tech.thdev.app.R
import tech.thdev.base.view.BaseFragment

/**
 * Created by tae-hwan on 12/11/16.
 *
 * A placeholder fragment containing a simple view.
 */

class PlaceholderFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_main

    companion object {
        fun newInstance() = PlaceholderFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}