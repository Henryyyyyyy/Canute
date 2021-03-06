package me.henry.canuteec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import me.henry.canutecore.app.AccountManager;
import me.henry.canutecore.app.IUserChecker;
import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.ui.launcher.ILauncherListener;
import me.henry.canutecore.ui.launcher.OnLauncherFinishTag;
import me.henry.canutecore.ui.launcher.ScrollLauncherTag;
import me.henry.canutecore.util.CanutePreference;
import me.henry.canutecore.util.timer.BaseTimerTask;
import me.henry.canutecore.util.timer.ITimerListener;
import me.henry.canuteec.R;
import me.henry.canuteec.R2;


/**
 * Created by zj on 2017/8/16.
 * me.henry.canuteec.launcher
 */

public class LauncherDelegate extends CanuteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;
    private Timer mTimer;

    private ILauncherListener mILauncherListener = null;
     @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }
    private int mCount = 5;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!CanutePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了APP,
            //在自己的manager里面检测
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

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                }
                if (mCount < 0) {
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                        checkIsShowScroll();
                    }
                }
            }
        });

    }
}
