package tech.thdev.app.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import tech.thdev.app.adapter.model.SectionsPagerModel
import tech.thdev.app.view.basic.PlaceholderFragment
import tech.thdev.app.view.one.SampleOneFragment
import java.util.*

/**
 * Created by tae-hwan on 12/11/16.
 *
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

class SectionsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager), SectionsPagerModel {

    private val itemList = ArrayList<Int>()

    override fun getItem(position: Int) = when (position) {
        0 -> SampleOneFragment()
        else -> PlaceholderFragment.newInstance(position + 1)
    }

    // itemList size
    override fun getCount() = itemList.size

    override fun setListItem(position: Int) {
        itemList.add(position)
    }

    override fun getListItem(position: Int) = itemList[position]
}