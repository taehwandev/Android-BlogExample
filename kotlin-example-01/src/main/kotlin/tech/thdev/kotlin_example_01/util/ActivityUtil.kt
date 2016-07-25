package tech.thdev.kotlin_example_01.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Tae-hwan on 7/21/16.
 */

/**
 * Content fragment replace.
 */
fun AppCompatActivity.setContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    supportFragmentManager?.beginTransaction()?.replace(frameId, fragment)?.commit()
}

/**
 * find fragment by IdRes.
 */
fun AppCompatActivity.getContentFragment(@IdRes frameId: Int):
        Fragment = supportFragmentManager.findFragmentById(frameId)
