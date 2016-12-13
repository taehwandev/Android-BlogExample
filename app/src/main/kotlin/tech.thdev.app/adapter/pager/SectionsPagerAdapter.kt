package tech.thdev.app.adapter.pager

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import tech.thdev.app.adapter.pager.model.SectionsPagerModel
import tech.thdev.app.view.basic.PlaceholderFragment
import tech.thdev.app.view.dailyboxoffice.DailyBoxOfficeFragment
import tech.thdev.app.view.weekboxoffice.WeeklyBoxOfficeFragment
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
        1 -> DailyBoxOfficeFragment()
        2 -> WeeklyBoxOfficeFragment()
        else -> PlaceholderFragment.newInstance()
    }

    // itemList size
    override fun getCount() = itemList.size

    override fun setListItem(position: Int) {
        itemList.add(position)
    }

    override fun getListItem(position: Int) = itemList[position]
}