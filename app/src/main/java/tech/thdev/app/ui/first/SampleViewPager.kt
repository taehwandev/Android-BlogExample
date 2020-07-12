package tech.thdev.app.ui.first

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SampleViewPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = mutableListOf<Fragment>()

    override fun getItemCount(): Int =
        fragmentList.size

    override fun createFragment(position: Int): Fragment =
        fragmentList[position]

    fun addItem(fragment: Fragment) {
        fragmentList.add(fragment)
    }
}