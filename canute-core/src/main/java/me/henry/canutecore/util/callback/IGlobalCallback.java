package me.henry.canutecore.util.callback;

import android.support.annotation.Nullable;


/**
 * 指定数据类型，利于拓展
 * @param <T>
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
