package me.henry.canuteec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;


import me.henry.canutecore.app.AccountManager;
import me.henry.canutecore.app.IUserChecker;
import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.ui.launcher.ILauncherListener;
import me.henry.canutecore.ui.launcher.LauncherHolderCreator;
import me.henry.canutecore.ui.launcher.OnLauncherFinishTag;
import me.henry.canutecore.ui.launcher.ScrollLauncherTag;
import me.henry.canutecore.util.CanutePreference;
import me.henry.canuteec.R;

/**
 * Created by zj on 2017/8/16.
 * me.henry.canuteec.launcher
 */

public class LauncherScrollDelegate extends CanuteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer>mConvenientBanner=null;
    private static final ArrayList<Integer>INTEGERS=new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
                if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);




    }
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
initBanner();
    }

    @Override
    public void onItemClick(int position) {
if (position==INTEGERS.size()-1){
    CanutePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
    //检查用户是否已经登录
    AccountManager.checkAccount(new IUserChecker() {
        @Override
        public void onSignIn() {
            if (mILauncherListener != null) {
                mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
            }
        }

        @Override
        public void onNotSignIn() {
            if (mILauncherListener != null) {
                mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
            }
        }
    });
}
    }
}
