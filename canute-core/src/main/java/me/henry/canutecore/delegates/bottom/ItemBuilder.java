package me.henry.canutecore.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by zj
 * 简单工厂模式，用建造者。
 * 1。弄一个builder其实就是建造这个对象
 * 2。在这个类里面定义需要的内容，然后通过builder赋值这些内容
 * 3。最后build返回这个类里面的某些对象
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
