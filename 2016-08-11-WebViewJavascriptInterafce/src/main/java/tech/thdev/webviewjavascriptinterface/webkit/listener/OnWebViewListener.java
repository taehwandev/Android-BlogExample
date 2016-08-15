package tech.thdev.webviewjavascriptinterface.webkit.listener;

/**
 * Created by tae-hwan on 8/15/16.
 */

public interface OnWebViewListener {

    void onFinish(String url);

    void onUrlChange(String url);
}
