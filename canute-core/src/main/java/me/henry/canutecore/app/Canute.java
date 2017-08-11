package me.henry.canutecore.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zj on 2017/8/9.
 * me.henry.canute.app
 * --8.9问：这里就只能初始化configurator了吗，其他东西怎么办
 */

public class Canute {
    public static  Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getCanuteConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}

