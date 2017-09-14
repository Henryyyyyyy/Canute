package me.henry.canute;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.web.event.TestEvent;
import me.henry.canuteec.database.DataBaseManager;
import me.henry.canuteec.icon.FontEcModule;


/**
 * Created by zj on 2017/8/9.
 * me.henry.ecapp
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Canute.init(this)
                .withApiHost("http://116.196.95.67/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppId("wxa3959a378ce8be08")
                .withWeChatAppSecret("wxa3959a378ce8be08")
                .withJavascriptInterface("canute")
                .withWebEvent("test", new TestEvent())
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
