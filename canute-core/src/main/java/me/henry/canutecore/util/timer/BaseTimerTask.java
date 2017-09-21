package me.henry.canutecore.util.timer;

import java.util.TimerTask;

/**
 * Created by zj on 2017/8/16.
 * me.henry.canutecore.util.timer
 */

public class BaseTimerTask extends TimerTask{
private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
