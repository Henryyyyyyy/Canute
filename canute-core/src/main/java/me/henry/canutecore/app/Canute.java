package me.henry.canutecore.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zj on 2017/8/9.
 * me.henry.canute.app
 * --8.9问：这里就只能初始化configurator了吗，其他东西怎么办
 */

public class Canute {
    /**
     * 这里的init先进行基本的初始化操作，
     * 然后可以通过.xxx再进行相应的的后续初始化操作
     * @param context
     * @return
     */
    public static  Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Context getAppContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getCanuteConfigs();
    }

}

