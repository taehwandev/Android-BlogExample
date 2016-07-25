package tech.thdev.kotlin_example_01.util

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Tae-hwan on 7/21/16.
 */
fun AppCompatActivity.setContentFragment(frameId: Int, fragment: Fragment) {
    val f: Fragment? = supportFragmentManager.findFragmentById(frameId)
    f?.let { }
    supportFragmentManager?.beginTransaction()?.add(frameId, fragment)?.commit()
}