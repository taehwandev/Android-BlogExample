package tech.thdev.webviewexample.webkit.listener;

/**
 * Created by tae-hwan on 8/14/16.
 */

public interface OnWebViewListener {

    void onFinish(String url);

    void onUrlChange(String url);
}
