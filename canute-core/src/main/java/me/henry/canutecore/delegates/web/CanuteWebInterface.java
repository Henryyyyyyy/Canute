package me.henry.canutecore.delegates.web;

import com.alibaba.fastjson.JSON;

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
    public String event(String params){
        final String action= JSON.parseObject(params).getString("action");
        return null;
    }
}
