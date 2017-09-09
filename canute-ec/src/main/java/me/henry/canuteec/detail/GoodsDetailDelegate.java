package me.henry.canuteec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canuteec.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by zj on 2017/9/9.
 * me.henry.canuteec.detail
 */

public class GoodsDetailDelegate extends CanuteDelegate{
public static GoodsDetailDelegate create(){
    return new GoodsDetailDelegate();
}

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
