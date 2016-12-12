package tech.thdev.app.view.basic

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.*
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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
    }
}