package me.henry.canute;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;


import me.henry.canutecore.activity.ProxyActivity;

import me.henry.canutecore.delegates.CanuteDelegate;

import me.henry.canuteec.launcher.LauncherDelegate;
import me.henry.canuteec.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();

        if (actionBar!=null){

            actionBar.hide();
        }
    }

    @Override
    public CanuteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
    //  Toast.makeText(Canute.getAppContext(),"haha",Toast.LENGTH_SHORT).show();


}
