package me.henry.canute;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import me.henry.canutecore.delegates.CanuteDelegate;

/**
 * Created by zj on 2017/8/14.
 * me.henry.canute
 */

public class ExampleDelegate extends CanuteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
