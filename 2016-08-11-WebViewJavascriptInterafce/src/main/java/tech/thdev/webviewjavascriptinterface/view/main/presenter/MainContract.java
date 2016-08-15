package tech.thdev.webviewjavascriptinterface.view.main.presenter;

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter;
import tech.thdev.kotlin_example_01.base.presenter.BaseView;
import tech.thdev.webviewjavascriptinterface.webkit.CustomJavaScript;
import tech.thdev.webviewjavascriptinterface.webkit.listener.OnCustomJavaScriptListener;
import tech.thdev.webviewjavascriptinterface.webkit.listener.OnWebViewListener;

/**
 * Created by Tae-hwan on 8/8/16.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        /**
         * Load url
         */
        void loadUrl(String url);

        /**
         * WebKeyword update.
         */
        void updateKeyword(String keyword);

        /**
         * WebView Change event.
         */
        void changeWebView(String url);

        /**
         * Url Change event
         */
        void changeUrl(String url);
    }

    interface Presenter extends BasePresenter<View>, OnCustomJavaScriptListener, OnWebViewListener {

        CustomJavaScript getCustomJavaScript();
    }
}
