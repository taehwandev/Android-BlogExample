package tech.thdev.webviewjavascriptinterface.view.main

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import tech.thdev.kotlin_example_01.base.view.BaseFragment
import tech.thdev.webviewjavascriptinterface.R
import tech.thdev.webviewjavascriptinterface.util.hideKeyboard
import tech.thdev.webviewjavascriptinterface.view.main.presenter.KotlinContract
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebChromeClient
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebViewClient
import tech.thdev.webviewjavascriptinterface.webkit.KtWebView

/**
 * Created by Tae-hwan on 8/11/16.
 */
class KotlinFragment(var url: String = ""): BaseFragment<KotlinContract.Presenter>(), KotlinContract.View {

    override fun getLayout(): Int = R.layout.fragment_kotlin

    private val etUrl by lazy {
        activity.findViewById(R.id.et_url) as EditText
    }

    private val webView by lazy {
        view?.findViewById(R.id.web_view) as KtWebView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        etUrl.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_GO) {
                var url = etUrl.text.toString()
                if (TextUtils.isEmpty(url)) {
                    url = this.url
                }
                webView.loadUrl(url)
                context.hideKeyboard(etUrl)
            }
            true
        }

        webView.setWebChromeClient(CustomWebChromeClient(activity))
        webView.setWebViewClient(CustomWebViewClient(presenter))
        webView.init()

        webView.addJavascriptInterface(presenter?.getOnCustomJavaScriptListener(), "WebViewTest")

        etUrl.setText(url)
        webView.loadUrl(url)
    }

    override fun updateKeyword(keyword: String?) {
        Toast.makeText(context, keyword, Toast.LENGTH_SHORT).show()
    }

    override fun changeUrl(url: String?) {
        url?.let { webView.loadUrl(url) }
    }

    override fun updateUrl(url: String?) {
        etUrl.setText(url)
    }

    override fun hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(webView.windowToken, 0)
    }
}