package tech.thdev.kotlin_example_01

import tech.thdev.kotlin_example_01.base.BaseFragment

/**
 * Created by Tae-hwan on 7/21/16.
 */

class ExFragment : BaseFragment() {
    companion object {

        val instance: ExFragment
            get() = ExFragment()
    }
}
