package tech.thdev.webviewjavascriptinterface.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Tae-hwan on 8/8/16.
 */

fun AppCompatActivity.replaceContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    supportFragmentManager?.beginTransaction()?.replace(frameId, fragment)?.commit()
}