package me.henry.canute;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.web.event.TestEvent;
import me.henry.canuteec.database.DataBaseManager;
import me.henry.canuteec.icon.FontEcModule;


/**
 *
 * Created by zj on 2017/8/9.
 * me.henry.ecapp
 */
//@Target(ElementType.TYPE)//表示在类上面使用的
//@Retention(RetentionPolicy.SOURCE)//生成源码的时候使用的,对性能没有影响
//public @interface EntryGenerator {
//    String packageName();//这些就是在注解上面要添加的参数
//    Class<?>entryTemplate();
//}

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
