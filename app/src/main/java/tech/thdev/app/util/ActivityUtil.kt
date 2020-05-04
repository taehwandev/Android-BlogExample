package tech.thdev.app.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Tae-hwan on 8/8/16.
 */

fun AppCompatActivity.replaceContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).commitAllowingStateLoss()
}

/**
 * FindContentFragment
 */
fun AppCompatActivity.findContentFragment(@IdRes frameId: Int) =
    supportFragmentManager.findFragmentById(frameId)

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}