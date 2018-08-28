package com.sample.android.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * version v1.0
 * 项目: sample-user-manager-application
 * 包名: com.sample.android.server
 * 功能描述: 数据库操作类
 * 作者: 杨松松
 * 创建时间: 2017/11/19 17:05
 */
public class DatabaseDao {

    /**
     * SQLiteDataBase对象
     */

    private static SQLiteDatabase db;
    /**
     * 上下文对象
     */

    private static Context mContext;

    protected DatabaseDao() {
    }

    /**
     * DatabaseDao实例
     */
    private static class DataBaseHelp {
        private static DatabaseDao INSTANCE = new DatabaseDao();
    }

    /**
     * 单利模式
     *
     * @param context
     * @return
     */
    public static DatabaseDao getInstance(Context context) {
        if (db == null) {
            db = new DatabaseOpenHelper(context).getWritableDatabase();
            mContext = context;
        }
        return DataBaseHelp.INSTANCE;
    }

    /**
     * 获取SQLiteDataBase对象
     *
     * @return
     */
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 设置SQLIteDataBase对象
     *
     * @param db
     */
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * 获得数据库帮助类
     *
     * @return
     */
    public DatabaseOpenHelper getHelper() {
        return new DatabaseOpenHelper(mContext);
    }

    /**
     * 获得上下文对象
     *
     * @return
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * 设置上下文对象
     *
     * @param mContext
     */
    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
