package tech.thdev.app.util

/**
 * Created by tae-hwan on 8/14/16.
 */
fun String?.checkUrlText(): String {
    if (this?.startsWith("http://") == true) {
        this?.replace("http://", "https://")
    } else {

    }
    return this?.takeIf { it.startsWith("http://") }?.replace("http://", "https://") ?: ("https://$this" ?: "")
}