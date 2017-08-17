package me.henry.canutecore.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import me.henry.canutecore.app.Canute;

/**
 * Created by zj on 2017/8/17.
 * me.henry.canutecore.util.dimen
 */

public final class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Canute.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources =  Canute.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
