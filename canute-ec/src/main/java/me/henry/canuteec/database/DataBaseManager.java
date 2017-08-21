package me.henry.canuteec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import java.util.zip.ZipEntry;

/**
 * Created by zj on 2017/8/21.
 * me.henry.canuteec.database
 */

public class DataBaseManager {
    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;
private static  final  class Holder{
    private static  final  DataBaseManager INSTANCE =new DataBaseManager();
}
    public static DataBaseManager getInstance(){
        return Holder.INSTANCE;
    }
    private DataBaseManager(){

    }
    public DataBaseManager  init(Context context){
        initData(context);
        return this;
    }
    private void initData(Context context) {
        final ReleaseOpenHelper helper=new ReleaseOpenHelper(context,"canute_ec.db");
        final Database db=helper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserProfileDao();
    }
    public final UserProfileDao getDao(){
        return mDao;
    }
}
