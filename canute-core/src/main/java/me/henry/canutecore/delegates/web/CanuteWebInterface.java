package me.henry.canutecore.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.delegates.web.event.Event;
import me.henry.canutecore.delegates.web.event.EventManager;
import me.henry.canutecore.util.log.CanuteLogger;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canutecore.delegates.web
 */

public class CanuteWebInterface {
    private final WebDelegate DELEGATE;

    private CanuteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }
    public static CanuteWebInterface create(WebDelegate delegate){
        return new CanuteWebInterface(delegate);
    }

    /**
     * 传入json数据
     * @param params
     * @return
     */
    @JavascriptInterface
    public String event(String params){
        final String action= JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        CanuteLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
