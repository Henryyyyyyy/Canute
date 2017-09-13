package me.henry.canuteec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.delegates.bottom.BottomItemDelegate;
import me.henry.canutecore.delegates.web.WebDelegateImpl;
import me.henry.canuteec.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by zj on 2017/9/13.
 * me.henry.canuteec.main.discover
 */

public class DiscoverDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate=WebDelegateImpl.create("index.html");
        loadRootFragment(R.id.web_discovery_container,delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
