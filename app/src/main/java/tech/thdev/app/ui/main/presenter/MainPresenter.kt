package tech.thdev.app.ui.main.presenter

import tech.thdev.app.base.presenter.AbstractPresenter
import tech.thdev.app.ui.main.presenter.MainContract.Presenter
import tech.thdev.app.webkit.CustomJavaScript

/**
 * Created by Tae-hwan on 8/8/16.
 */
class MainPresenter(
    override val customJavaScript: CustomJavaScript = CustomJavaScript()
) : AbstractPresenter<MainContract.View>(), Presenter {

    init {
        customJavaScript.setOnCustomJavascriptListener(this)
    }

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
        view.onPresenter(this)
    }

    override fun onUpdateKeyword(keyword: String) {
        if (isAttachView()) {
            view!!.updateKeyword(keyword)
        }
    }

    override fun onChangeWebView(url: String) {
        if (isAttachView()) {
            view!!.changeWebView(url)
        }
    }

    override fun onFinish(url: String) {
        if (isAttachView()) {
            view!!.changeUrl(url)
        }
    }

    override fun onUrlChange(url: String) {
        if (isAttachView()) {
            view!!.changeUrl(url)
        }
    }

    override fun hideKeyboard() {
        if (isAttachView()) {
            view!!.hideKeyboard()
        }
    }
}