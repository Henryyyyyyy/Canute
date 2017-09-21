package me.henry.canutecore.wechat.template;

import me.henry.canutecore.util.log.CanuteLogger;
import me.henry.canutecore.wechat.BaseWXEntryActivity;
import me.henry.canutecore.wechat.CanuteWechat;

/**
 * Created by zj on 2017/8/30.
 * me.henry.canutecore.wechat.template
 */

public class WXEntryTemplate extends BaseWXEntryActivity{
    @Override
    protected void onResume() {
        super.onResume();
        CanuteLogger.e("wxtemplate","onresume");
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        CanuteLogger.e("wxtemplate","onSignInSuccess.userinfo="+userInfo);
        CanuteWechat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
