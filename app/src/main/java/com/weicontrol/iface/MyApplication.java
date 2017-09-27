package com.weicontrol.iface;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.weicontrol.iface.event.ShareEvent;

import cn.jpush.android.api.JPushInterface;
import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.web.event.TestEvent;
import me.henry.canutecore.util.callback.CallbackManager;
import me.henry.canutecore.util.callback.CallbackType;
import me.henry.canutecore.util.callback.IGlobalCallback;
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
               // .withApiHost("http://116.196.95.67/RestServer/api/")
                .withApiHost("http://192.168.11.105:8080/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppId("wxfa48d65f290ff3b8")//iface
                .withWeChatAppSecret("wxa3959a378ce8be08")
                .withJavascriptInterface("canute")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .configure();
        DataBaseManager.getInstance().init(this);
        initStetho();
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance().addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Canute.getAppContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Canute.getAppContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Canute.getAppContext())) {
                            JPushInterface.stopPush(Canute.getAppContext());
                        }
                    }
                });
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

}
