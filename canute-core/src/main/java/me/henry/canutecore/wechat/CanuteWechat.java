package me.henry.canutecore.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.app.ConfigKey;
import me.henry.canutecore.wechat.callback.IWeChatSignInCallback;

/**
 * Created by zj on 2017/8/30.
 * me.henry.canutecore.wechat
 */

public class CanuteWechat {
    public static final String APP_ID = Canute.getConfiguration(ConfigKey.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Canute.getConfiguration(ConfigKey.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;
    private static final class Holder{
        private static final CanuteWechat INSTANCE=new CanuteWechat();
    }
    public static CanuteWechat getInstance(){
        return Holder.INSTANCE;
    }


    private CanuteWechat() {
        final Activity activity = Canute.getConfiguration(ConfigKey.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public CanuteWechat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
