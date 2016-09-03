package tech.thdev.webviewexample.webkit;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import tech.thdev.webviewexample.webkit.listener.OnWebViewListener;

/**
 * Created by Tae-hwan on 8/4/16.
 */

public class CustomWebViewClient extends WebViewClient {

    private OnWebViewListener listener;

    public CustomWebViewClient(OnWebViewListener listener) {
        this.listener = listener;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        shouldOverrideUrlLoading(view, request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (listener != null) {
            listener.onUrlChange(url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (listener != null) {
            listener.onFinish(url);
        }
        super.onPageFinished(view, url);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

        Log.i("TAG", "error number : " + error.getErrorCode());
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Log.i("TAG", "error number : " + errorCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);

        Log.d("TAG", "onReceivedHttpError " + errorResponse.getStatusCode());
    }
}
