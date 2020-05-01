package tech.thdev.app.webkit

import android.content.Context
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog

/**
 * Created by Tae-hwan on 8/4/16.
 */
class CustomWebChromeClient(private val context: Context) : WebChromeClient() {

    private fun showDialog(message: String?, result: JsResult?) {
        val alertDialog = AlertDialog.Builder(context).setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> result?.confirm() }
            .setCancelable(false).create()
        alertDialog.setOnDismissListener { result?.cancel() }
        alertDialog.show()
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        showDialog(message, result)
        return true
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        showDialog(message, result)
        return true
    }
}