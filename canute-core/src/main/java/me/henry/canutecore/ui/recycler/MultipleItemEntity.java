package me.henry.canutecore.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by zj on 2017/9/8.
 * me.henry.canutecore.ui.recycler
 */

public class MultipleItemEntity implements MultiItemEntity{
   //recycler可能会有很多数据，防止内存溢出
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE=new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCES=
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUEUE);
    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCES.get().putAll(fields);
    }
    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCES.get().get(MultipleFields.ITEM_TYPE);
    }
    public final <T> T getField(Object key){
        return (T)FIELDS_REFERENCES.get().get(key);
    }
    public final LinkedHashMap<?,?> getFields(){
        return  FIELDS_REFERENCES.get();
    }
    public final MultipleItemEntity setField(Object key,Object value){
        FIELDS_REFERENCES.get().put(key,value);
        return this;
    }
}
