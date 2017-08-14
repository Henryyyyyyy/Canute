package me.henry.canutecore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import me.henry.canutecore.R;
import me.henry.canutecore.delegates.CanuteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zj on 2017/8/14.
 * me.henry.canutecore.activity
 */

public abstract class ProxyActivity extends SupportActivity{
public  abstract CanuteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
