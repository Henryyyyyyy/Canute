package me.henry.canute;

import android.app.Application;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import me.henry.canutecore.app.Canute;


/**
 * Created by zj on 2017/8/9.
 * me.henry.ecapp
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Canute.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }

}
