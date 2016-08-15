package tech.thdev.webviewjavascriptinterface.util

/**
 * Created by tae-hwan on 8/14/16.
 */
fun String?.checkUrlText(): String {
    this?.let {
        if (it.startsWith("http://"))
            return it
        else
            return "http://" + it
    } ?: return ""
}