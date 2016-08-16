package tech.thdev.webviewjavascriptinterface.util

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Tae-hwan on 8/8/16.
 */

fun AppCompatActivity.replaceContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    supportFragmentManager?.beginTransaction()?.replace(frameId, fragment)?.commit()
}

/**
 * FindContentFragment
 */
fun AppCompatActivity.findContentFragment(@IdRes frameId: Int) = supportFragmentManager?.findFragmentById(frameId)

fun Context.hideKeyboard(view: View?) {
    view?.let {
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
    }
}