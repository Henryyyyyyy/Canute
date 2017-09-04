package me.henry.canuteec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.henry.canutecore.delegates.bottom.BottomItemDelegate;
import me.henry.canuteec.R;

/**
 * Created by henry on 2017/9/4.
 */

public class IndexDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
