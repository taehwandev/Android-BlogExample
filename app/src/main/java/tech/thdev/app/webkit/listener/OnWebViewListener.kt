package tech.thdev.app.webkit.listener

/**
 * Created by tae-hwan on 8/14/16.
 */
interface OnWebViewListener {
    fun onScroll(l: Int, t: Int, oldl: Int, oldt: Int)
    fun onFinish(url: String?)
    fun onUrlChange(url: String?)
    fun onProgressChanged(newProgress: Int)
}