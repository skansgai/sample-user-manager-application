package com.sample.android.datebase;

import android.database.sqlite.SQLiteDatabase;

import com.sample.android.config.DatabaseConfig;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author 杨松松
 * @ClassName: ${TYPE_NAME}
 * @Description: 数据库帮助类测试用例
 * @date 2017/11/19 22:50
 */
public class DatabaseOpenHelperTest {


    @Test
    public void onCreate() throws Exception {
        String SQL = "Create table " +
                DatabaseConfig.TABLE_NAME + "(" +
                DatabaseConfig.FIELD_ID + " integer primary key autoincrement," +
                DatabaseConfig.FIELD_NAME + " text(20)," +
                DatabaseConfig.FIELD_REMARK + " text(50)," +
                DatabaseConfig.FIELD_TIME + " text(50)" + ")";

        SQLiteDatabase sqLiteDatabase = mock(SQLiteDatabase.class);

        sqLiteDatabase.execSQL(SQL);

        verify(sqLiteDatabase).execSQL(SQL);

    }

    @Test
    public void onUpgrade() throws Exception {
    }

}