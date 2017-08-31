package me.henry.canutecore.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;



/**
 * Created by zj on 2017/8/30.
 * me.henry.canutecore.wechat
 *在微信里点击确认登录返回的activity
 */

public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //这个必须写在oncreate里面
        CanuteWechat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CanuteWechat.getInstance().getWXAPI().handleIntent(getIntent(),this);

    }
}
