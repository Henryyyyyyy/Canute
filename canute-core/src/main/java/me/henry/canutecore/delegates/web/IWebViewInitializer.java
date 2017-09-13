package me.henry.canutecore.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web
 */

public interface IWebViewInitializer {
    WebView initWebView(WebView webView);
    WebViewClient initWebViewClient();
    WebChromeClient initWebChromeClient();//针对页面内部控制的
}
