package tech.thdev.webviewjavascriptinterface.view.main.presenter;

import org.jetbrains.annotations.NotNull;

import tech.thdev.kotlin_example_01.base.presenter.AbstractPresenter;
import tech.thdev.webviewjavascriptinterface.webkit.CustomJavaScript;

/**
 * Created by Tae-hwan on 8/8/16.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    private CustomJavaScript customJavaScript;

    public MainPresenter() {
        customJavaScript = new CustomJavaScript();
        customJavaScript.setOnCustomJavascriptListener(this);
    }

    @Override
    public void attachView(@NotNull MainContract.View view) {
        super.attachView(view);
        view.onPresenter(this);
    }

    @Override
    public void onUpdateKeyword(String keyword) {
        if (isAttachView()) {
            getView().updateKeyword(keyword);
        }
    }

    @Override
    public CustomJavaScript getCustomJavaScript() {
        return customJavaScript;
    }

    @Override
    public void onChangeWebView(String url) {
        if (isAttachView()) {
            getView().changeWebView(url);
        }
    }
}
