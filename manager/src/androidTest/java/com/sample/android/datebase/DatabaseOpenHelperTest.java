package com.sample.android.datebase;

import com.sample.android.config.DatabaseConfig;

import org.junit.Test;

import io.requery.android.database.sqlite.SQLiteDatabase;
import static org.mockito.Mockito.*;

/**
 * @author 杨松松
 * @ClassName: ${TYPE_NAME}
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2017/11/19 23:00
 */
public class DatabaseOpenHelperTest {

    @Test
    public void onCreate() throws Exception {
        String SQL = "Create table " + DatabaseConfig.USER_TABLE_NAME + "(" +
                DatabaseConfig.FIELD_ID + " integer primary key autoincrement," +
                DatabaseConfig.FIELD_NAME + " text(20)," +
                DatabaseConfig.FIELD_REMARK + " text(50)" +
                DatabaseConfig.FIELD_TIME + " text(50)";
        SQLiteDatabase sqLiteDatabase = mock(SQLiteDatabase.class);

        sqLiteDatabase.execSQL(SQL);

        verify(sqLiteDatabase).execSQL(SQL);
    }

    @Test
    public void onUpgrade() throws Exception {
    }
}