package me.henry.canute;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import me.henry.canutecore.activity.ProxyActivity;
import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.ui.launcher.ILauncherListener;
import me.henry.canutecore.ui.launcher.OnLauncherFinishTag;
import me.henry.canuteec.sign.ISignListener;
import me.henry.canuteec.sign.SignInDelegate;
import me.henry.canuteec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener,ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Canute.getConfigurator().withActivity(this);
    }

    @Override
    public CanuteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
               Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
               // getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
              getSupportDelegate().startWithPop(new SignUpDelegate());
                break;
            default:
                break;
        }
    }
}
