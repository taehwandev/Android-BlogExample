package tech.thdev.kotlin_example_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.kotlin_example_01.R
import tech.thdev.kotlin_example_01.base.BaseFragment

/**
 * Created by Tae-hwan on 7/21/16.
 */
class MainFragment : BaseFragment() {

    companion object {
        @JvmStatic fun instance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater!!.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}