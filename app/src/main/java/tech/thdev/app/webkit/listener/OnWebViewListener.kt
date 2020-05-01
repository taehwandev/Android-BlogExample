package tech.thdev.app.webkit.listener

/**
 * Created by tae-hwan on 8/15/16.
 */
interface OnWebViewListener {
    fun onFinish(url: String)
    fun onUrlChange(url: String)
}