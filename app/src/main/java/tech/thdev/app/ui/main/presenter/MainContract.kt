package tech.thdev.app.ui.main.presenter

import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView
import tech.thdev.app.webkit.CustomJavaScript
import tech.thdev.app.webkit.listener.OnCustomJavaScriptListener
import tech.thdev.app.webkit.listener.OnWebViewListener

/**
 * Created by Tae-hwan on 8/8/16.
 */
interface MainContract {
    interface View : BaseView<Presenter> {
        /**
         * Load url
         */
        fun loadUrl(url: String)

        /**
         * WebKeyword update.
         */
        fun updateKeyword(keyword: String)

        /**
         * WebView Change event.
         */
        fun changeWebView(url: String)

        /**
         * Url Change event
         */
        fun changeUrl(url: String)

        /**
         * WebView event... hide keyboard
         */
        fun hideKeyboard()
    }

    interface Presenter : BasePresenter<View>, OnCustomJavaScriptListener, OnWebViewListener {
        val customJavaScript: CustomJavaScript
    }
}