package me.henry.canutecore.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.web.IPageLoadListener;
import me.henry.canutecore.delegates.web.WebDelegate;
import me.henry.canutecore.delegates.web.route.Router;
import me.henry.canutecore.ui.loader.CanuteLoader;
import me.henry.canutecore.util.log.CanuteLogger;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web.client
 */

public class WebViewClientImpl extends WebViewClient{
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Canute.getHandler();
    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }
    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        CanuteLogger.d("shouldOverrideUrlLoading",url);
        //false:webview处理，，，true：我们自己处理
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        CanuteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                CanuteLoader.stopLoading();
            }
        }, 1000);
    }
}
