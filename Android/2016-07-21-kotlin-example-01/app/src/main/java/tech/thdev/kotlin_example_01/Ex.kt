package tech.thdev.kotlin_example_01

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

import kotlin.jvm.internal.Intrinsics.checkNotNull

/**
 * Created by Tae-hwan on 7/21/16.
 */

object Ex {

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.

     */
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
}
