package me.henry.canuteec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import me.henry.canutecore.delegates.bottom.BasebottomDelegate;
import me.henry.canutecore.delegates.bottom.BottomItemDelegate;
import me.henry.canutecore.delegates.bottom.BottomTabBean;
import me.henry.canutecore.delegates.bottom.ItemBuilder;
import me.henry.canuteec.main.cart.ShopCartDelegate;
import me.henry.canuteec.main.discover.DiscoverDelegate;
import me.henry.canuteec.main.index.IndexDelegate;
import me.henry.canuteec.main.personal.PersonalDelegate;
import me.henry.canuteec.main.sort.SortDelegate;

/**
 * Created by henry on 2017/9/4.
 */

public class EcBottomDelegate extends BasebottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}

