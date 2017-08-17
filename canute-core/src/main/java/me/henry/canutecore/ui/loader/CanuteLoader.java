package me.henry.canutecore.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import me.henry.canutecore.R;
import me.henry.canutecore.util.dimen.DimenUtil;

/**
 * Created by zj on 2017/8/17.
 * me.henry.canutecore.ui.loader
 */

public class CanuteLoader {
    private static final int LOADER_SIZE_SCALE=8;
    private static final int LOADER_OFFSET_SCALE=10;
    private static final ArrayList<AppCompatDialog>LOADERS=new ArrayList<>();
    private static final String DEFAULT_LOADER=LoaderStyle.BallRotateIndicator.name();

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeigh= DimenUtil.getScreenHeight();
        final Window dialogWindow=dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceHeigh/LOADER_SIZE_SCALE;
            lp.height= lp.height+deviceHeigh/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void  showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                    //dismiss只是单纯的消失，不会调用cancel的回调
                }
            }
        }
    }
}
