package tech.thdev.webviewjavascriptinterface.view.main

import android.os.Bundle
import android.view.View
import tech.thdev.kotlin_example_01.base.view.BaseFragment
import tech.thdev.webviewjavascriptinterface.R
import tech.thdev.webviewjavascriptinterface.view.main.presenter.KotlinContract
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebChromeClient
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebViewClient
import tech.thdev.webviewjavascriptinterface.webkit.KtWebView

/**
 * Created by Tae-hwan on 8/11/16.
 */
class KotlinFragment(var url: String = ""): BaseFragment<KotlinContract.Presenter>() {

    override fun getLayout(): Int = R.layout.fragment_kotlin

    private val webView by lazy {
        view?.findViewById(R.id.web_view) as KtWebView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        webView.setWebChromeClient(CustomWebChromeClient(activity))
        webView.setWebViewClient(CustomWebViewClient())
        webView.init()

        webView.addJavascriptInterface(presenter?.getOnCustomJavaScriptListener(), "WebViewTest")

        webView.loadUrl(url)
    }
}