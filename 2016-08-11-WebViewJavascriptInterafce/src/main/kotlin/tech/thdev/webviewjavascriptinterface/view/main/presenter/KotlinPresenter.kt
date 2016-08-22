package tech.thdev.webviewjavascriptinterface.view.main.presenter

import tech.thdev.kotlin_example_01.base.presenter.AbstractPresenter
import tech.thdev.webviewjavascriptinterface.webkit.listener.OnCustomJavaScriptListener

/**
 * Created by Tae-hwan on 8/11/16.
 */
class KotlinPresenter: AbstractPresenter<KotlinContract.View>(), KotlinContract.Presenter {

    override fun attachView(view: KotlinContract.View) {
        super.attachView(view)
        view.onPresenter(this)
    }

    override fun onUpdateKeyword(keyword: String?) {
        view?.updateKeyword(keyword)
    }

    override fun getOnCustomJavaScriptListener(): OnCustomJavaScriptListener = this

    override fun onChangeWebView(url: String?) {
        view?.changeUrl(url)
    }

    override fun onFinish(url: String?) {
        view?.updateUrl(url)
    }

    override fun onUrlChange(url: String?) {
        view?.updateUrl(url)
    }

    override fun hideKeyboard() {
        view?.hideKeyboard()
    }
}