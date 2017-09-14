package me.henry.canutecore.delegates.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by zj on 2017/9/14.
 * com.flj.latte.delegates.web.event
 */

public class TestEvent extends Event{
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
