package tech.thdev.customwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import tech.thdev.customwebview.webkit.CustomWebChromeClient;
import tech.thdev.customwebview.webkit.CustomWebView;
import tech.thdev.customwebview.webkit.CustomWebViewClient;

public class MainActivity extends AppCompatActivity {

    private CustomWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (CustomWebView) findViewById(R.id.web_view);

        webView.setWebViewClient(new CustomWebViewClient());
        webView.setWebChromeClient(new CustomWebChromeClient(this));

        webView.loadUrl("http://coocha.co.kr/asdofhas.do");

        List<String> itemList = new ArrayList<>();
        itemList.add("A");
        itemList.add(null);
        itemList.add("B");

        for (String text : itemList) {
            if (text != null) {
                Log.d("TAG", "Text : " + text);
            }
        }
    }
}