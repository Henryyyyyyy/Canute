package me.henry.canuteec.main.index.search;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

import me.henry.canutecore.ui.recycler.DataConverter;
import me.henry.canutecore.ui.recycler.MultipleFields;
import me.henry.canutecore.ui.recycler.MultipleItemEntity;
import me.henry.canutecore.util.CanutePreference;


/**
 * Created by 傅令杰
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr =
                CanutePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
