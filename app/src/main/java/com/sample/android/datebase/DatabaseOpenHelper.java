package com.sample.android.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sample.android.config.DatabaseConfig;
import com.sample.android.util.LogUtil;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.server
 * 功能描述:数据库帮助类
 * 作者:杨松松
 * 创建时间:2017/11/19 17:09
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    /** 日志打印标签 */
    private static final String TAG = DatabaseOpenHelper.class.getName();

    /**
     * 创建用户信息表sql
     */
    private static String SQL_CREATE_USER_TABLE = "Create table " + DatabaseConfig.TABLE_NAME + "(" +
            DatabaseConfig.ID + " integer primary key autoincrement," +
            DatabaseConfig.LOGI_NNAME + " text(50)," +
            DatabaseConfig.ZH_NAME + " text(50)," +
            DatabaseConfig.EN_NAME + " text(50)," +
            DatabaseConfig.SEX + " text(10)," +
            DatabaseConfig.BIRTH + " text(20)," +
            DatabaseConfig.EMAIL + " text(50)," +
            DatabaseConfig.PHONE + " text(50)," +
            DatabaseConfig.ADDRESS + " text(50)," +
            DatabaseConfig.PASSWORD + " text(50)," +
            DatabaseConfig.ORDER_BY + " text(50)," +
            DatabaseConfig.CREATE_TIME + " text(50)," +
            DatabaseConfig.UPDATE_TIME + " text(50)," +
            DatabaseConfig.EN_NAME + " text(50))";

    public DatabaseOpenHelper(Context context) {
        super(context, DatabaseConfig.DATABASE_NAME, null, DatabaseConfig.DATABASE_VERSION);
        LogUtil.i(TAG, "");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
        LogUtil.i(TAG, "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.i(TAG, "");
        updataSQLite(db, oldVersion);
    }

    /**
     * 创建用户信息表
     * @param db 数据库对象
     */
    private void createUserTable(SQLiteDatabase db) {
        LogUtil.i(TAG, "");
        db.execSQL(DatabaseOpenHelper.SQL_CREATE_USER_TABLE);
    }

    /**
     * 数据升级方法
     * @param db 数据库对象
     * @param oldVersion 数据旧版本
     */
    private void updataSQLite(SQLiteDatabase db, int oldVersion) {
        LogUtil.i(TAG, "");
        db.execSQL("drop table if exists " + DatabaseConfig.DATABASE_NAME);
        switch (oldVersion) {
            case 1:
                createUserTable(db);
                break;
            case 2:
                break;
        }
    }
}
