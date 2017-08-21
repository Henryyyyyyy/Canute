package me.henry.canuteec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zj on 2017/8/21.
 * me.henry.canuteec.database
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
