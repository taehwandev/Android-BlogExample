package tech.thdev.webviewexample;

import org.jetbrains.annotations.NotNull;

import tech.thdev.kotlin_example_01.base.presenter.AbstractPresenter;
import tech.thdev.webviewjavascriptinterface.util.StringUtilKt;

/**
 * Created by tae-hwan on 8/14/16.
 */

public class MainPresenter extends AbstractPresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void attachView(@NotNull MainContract.View view) {
        super.attachView(view);

        view.onPresenter(this);
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
        if (isAttachView())
         getView().setUrl(url);
    }

    @Override
    public void onUrlChange(String url) {
        if (isAttachView())
            getView().setUrl(url);
    }
}
