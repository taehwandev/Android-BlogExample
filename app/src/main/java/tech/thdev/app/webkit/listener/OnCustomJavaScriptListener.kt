package tech.thdev.app.webkit.listener

/**
 * Created by Tae-hwan on 8/8/16.
 */
interface OnCustomJavaScriptListener {
    fun onUpdateKeyword(keyword: String)
    fun onChangeWebView(url: String)
    fun hideKeyboard()
}