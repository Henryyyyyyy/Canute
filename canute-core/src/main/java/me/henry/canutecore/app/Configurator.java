package me.henry.canutecore.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import me.henry.canutecore.delegates.web.event.Event;
import me.henry.canutecore.delegates.web.event.EventManager;

/**
 * Created by zj on 2017/8/9.
 * me.henry.canute.app
 * 用来装一些配置
 * ----遗留下来需要学习的，自定义iconify字体集
 *
 */

public class Configurator {
    private static final HashMap<Object, Object> CANUTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    public Configurator() {
        CANUTE_CONFIGS.put(ConfigKey.CONFIG_READY, false);
        CANUTE_CONFIGS.put(ConfigKey.HANDLER, HANDLER);
    }

    private static class Holder {
        //线程安全的懒汉模式，静态内部类
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getCanuteConfigs() {
        return CANUTE_CONFIGS;
    }



    private void checkConfiguration() {
        final boolean isReady = (boolean) CANUTE_CONFIGS.get(ConfigKey.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("configuration is not ready");
        }
    }
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CANUTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        //// TODO: 2017/8/30 优化一下，可以直接放value 
        return (T) CANUTE_CONFIGS.get(key);
    }

    public final Configurator withApiHost(String host) {
        CANUTE_CONFIGS.put(ConfigKey.API_HOST, host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }
    public final Configurator withWeChatAppId(String appId) {
        CANUTE_CONFIGS.put(ConfigKey.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        CANUTE_CONFIGS.put(ConfigKey.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        CANUTE_CONFIGS.put(ConfigKey.ACTIVITY, activity);
        return this;
    }
    public Configurator withJavascriptInterface(@NonNull String name) {
        CANUTE_CONFIGS.put(ConfigKey.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    /**
     * config之后就变成true了
     */
    public final void configure() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        initIcons();
        CANUTE_CONFIGS.put(ConfigKey.CONFIG_READY, true);
    }


}
