package me.henry.canutecore.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.delegates.web.route.RouteKeys;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web
 */

public abstract class WebDelegate extends CanuteDelegate{
    private WebView mWebView=null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE=new ReferenceQueue<>();
    private String mUrl=null;
    private boolean mIsWebViewAvaible=false;

    public WebDelegate() {
    }
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args= getArguments();
        mUrl=args.getString(RouteKeys.URL.name());
initWebView();
    }
    @SuppressLint("JavascriptInterface")
    private void  initWebView(){
        //防止初始化重复
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else {
            final IWebViewInitializer initializer=setInitializer();
            //为什么要以new的形式添加webview？因为容易造成内存泄漏
            if (initializer!=null){
                final WeakReference<WebView>webViewWeakReference=
                        new WeakReference<>(new WebView(getContext()),WEB_VIEW_QUEUE);
                mWebView=webViewWeakReference.get();
                mWebView=initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(CanuteWebInterface.create(this),"canute");
                mIsWebViewAvaible=true;
            }else {
                throw new NullPointerException("initializer is null");
            }
        }
    }
    public WebView getWebView(){
        if (mWebView==null){
            throw new NullPointerException("webview is null");
        }
        return  mIsWebViewAvaible?mWebView:null;
    }
    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("url IS NULL!");
        }
        return mUrl;
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvaible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
