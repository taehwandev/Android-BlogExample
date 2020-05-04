package tech.thdev.app.ui.main.presenter

import tech.thdev.app.base.presenter.AbstractPresenter
import tech.thdev.app.util.checkUrlText

/**
 * Created by tae-hwan on 8/14/16.
 */
class MainPresenter : AbstractPresenter<MainContract.View>(), MainContract.Presenter {

    companion object {
        private const val BLANK_PAGE = "about:blank"
    }

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        view.onPresenter(this)
    }

    override fun defaultLoadUrl() {
        view?.loadUrl(BLANK_PAGE)
    }

    override fun validationUrl(text: String?) {
        val url: String = text.checkUrlText()
        view?.loadUrl(url)
    }

    override fun onFinish(url: String?) {
        view?.setUrl(url)
    }

    override fun onUrlChange(url: String?) {
        view?.setUrl(url)
    }

    override fun onScroll(l: Int, t: Int, oldl: Int, oldt: Int) {
        val isTop = t < oldt
        view?.webViewScrollChanged(isTop)
    }

    override fun onProgressChanged(newProgress: Int) {
        view?.webViewProgressChanged(newProgress)
    }
}