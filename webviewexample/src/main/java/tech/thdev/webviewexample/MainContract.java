package tech.thdev.webviewexample;

import tech.thdev.kotlin_example_01.base.presenter.BasePresenter;
import tech.thdev.kotlin_example_01.base.presenter.BaseView;
import tech.thdev.webviewexample.webkit.listener.OnWebViewListener;

/**
 * Created by tae-hwan on 8/14/16.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void loadUrl(String url);

        /**
         * WebView request url change
         */
        void setUrl(String url);
    }

    interface Presenter extends BasePresenter<View>, OnWebViewListener {

        void validationUrl(String text);
    }
}
