package me.henry.canutecore.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.henry.canutecore.delegates.web.chromeclient.WebChromeClientImpl;
import me.henry.canutecore.delegates.web.client.WebViewClientImpl;
import me.henry.canutecore.delegates.web.route.RouteKeys;
import me.henry.canutecore.delegates.web.route.Router;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web
 */

public class WebDelegateImpl extends WebDelegate implements  IWebViewInitializer{
    private IPageLoadListener mIPageLoadListener = null;
    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }
    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
if (getUrl()!=null){
    //用原生的方式模拟web跳转
    Router.getInstance().loadPage(this,getUrl());
}
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final  WebViewClientImpl client=new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
