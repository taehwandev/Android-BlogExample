package tech.thdev.customwebview.webkit;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Tae-hwan on 8/4/16.
 */

public class CustomWebViewClient extends WebViewClient {

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
