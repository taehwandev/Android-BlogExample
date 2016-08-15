package tech.thdev.webviewexample.webkit.listener;

/**
 * Created by tae-hwan on 8/14/16.
 */

public interface OnWebViewListener {

    void onScroll(int l, int t, int oldl, int oldt);

    void onFinish(String url);

    void onUrlChange(String url);

    void onProgressChanged(int newProgress);
}
