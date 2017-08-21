package me.henry.canute;

import android.app.Application;
import android.app.TimePickerDialog;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.henry.canutecore.app.Canute;
import me.henry.canuteec.database.DataBaseManager;


/**
 * Created by zj on 2017/8/9.
 * me.henry.ecapp
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Canute.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .configure();
        DataBaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

}
