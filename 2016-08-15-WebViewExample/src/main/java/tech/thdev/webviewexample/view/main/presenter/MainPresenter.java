package tech.thdev.webviewexample.view.main.presenter;

import org.jetbrains.annotations.NotNull;

import tech.thdev.kotlin_example_01.base.presenter.AbstractPresenter;
import tech.thdev.webviewjavascriptinterface.util.StringUtilKt;

/**
 * Created by tae-hwan on 8/14/16.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    private static final String BLANK_PAGE = "about:blank";

    @Override
    public void attachView(@NotNull MainContract.View view) {
        super.attachView(view);

        view.onPresenter(this);
    }

    @Override
    public void defaultLoadUrl() {
        if (isAttachView()) {
            getView().loadUrl(BLANK_PAGE);
        }
    }

    @Override
    public void validationUrl(String text) {
        String url = StringUtilKt.checkUrlText(text);

        if (isAttachView()) {
            getView().loadUrl(url);
        }
    }

    @Override
    public void onFinish(String url) {
        if (BLANK_PAGE.equals(url)) {
            url = "";
        }

        if (isAttachView())
            getView().setUrl(url);
    }

    @Override
    public void onUrlChange(String url) {
        if (isAttachView())
            getView().setUrl(url);
    }

    @Override
    public void onScroll(int l, int t, int oldl, int oldt) {
        boolean isTop = t < oldt;
        if (isAttachView()) {
            getView().webViewScrollChanged(isTop);
        }
    }

    @Override
    public void onProgressChanged(int newProgress) {
        if (isAttachView()) {
            getView().webViewProgressChanged(newProgress);
        }
    }
}
