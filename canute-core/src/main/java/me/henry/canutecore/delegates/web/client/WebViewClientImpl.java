package me.henry.canutecore.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.henry.canutecore.delegates.web.WebDelegate;
import me.henry.canutecore.delegates.web.route.Router;
import me.henry.canutecore.util.log.CanuteLogger;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web.client
 */

public class WebViewClientImpl extends WebViewClient{
    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        CanuteLogger.d("shouldOverrideUrlLoading",url);
        //false:webview处理，，，true：我们自己处理
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
