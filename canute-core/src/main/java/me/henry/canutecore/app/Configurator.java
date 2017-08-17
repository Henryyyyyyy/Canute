package me.henry.canutecore.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

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

    public Configurator() {
        CANUTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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
        final boolean isReady = (boolean) CANUTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("configuration is not ready");
        }
    }
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) CANUTE_CONFIGS.get(key.name());
    }

    public final Configurator withApiHost(String host) {
        CANUTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
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
        initIcons();
        CANUTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }


}
