package me.henry.canuteec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.henry.canutecore.delegates.bottom.BottomItemDelegate;
import me.henry.canuteec.R;

/**
 * Created by zj on 2017/9/6.
 * me.henry.canuteec.main.sort
 */

public class SortDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
