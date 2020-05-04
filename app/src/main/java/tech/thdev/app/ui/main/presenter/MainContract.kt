package tech.thdev.app.ui.main.presenter

import tech.thdev.app.base.presenter.BasePresenter
import tech.thdev.app.base.presenter.BaseView
import tech.thdev.app.webkit.listener.OnWebViewListener

/**
 * Created by tae-hwan on 8/14/16.
 */
interface MainContract {
    interface View : BaseView<Presenter> {
        fun loadUrl(url: String)

        /**
         * WebView request url change
         */
        fun setUrl(url: String?)

        /**
         * WebView scroll change
         *
         * @param isTop If true, scroll to the top.
         */
        fun webViewScrollChanged(isTop: Boolean)

        /**
         * WebView Progress changed
         */
        fun webViewProgressChanged(newProgress: Int)
    }

    interface Presenter : BasePresenter<View>, OnWebViewListener {
        fun validationUrl(text: String?)

        /**
         * Default url load...
         */
        fun defaultLoadUrl()
    }
}