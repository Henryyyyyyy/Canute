package me.henry.canutecore.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import me.henry.canutecore.R;
import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.CanuteDelegate;

/**
 * Created by henry on 2017/9/4.
 */

public abstract class BottomItemDelegate extends CanuteDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

//    @Override
//    public void onResume() {
//        super.onResume();
//        View rootview=getView();
//        if (rootview!=null){
//            rootview.setFocusableInTouchMode(true);
//            rootview.requestFocus();
//            rootview.setOnKeyListener(this);
//        }
//    }


    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Canute.getAppContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
