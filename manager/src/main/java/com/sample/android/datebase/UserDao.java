package com.sample.android.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.sample.android.config.DatabaseConfig;
import com.sample.android.model.entity.UserEntity;
import com.sample.android.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.datebase
 * 功能描述:user数据操作DAO-真正的与数据库交互
 * 作者:杨松松
 * 创建时间:2018/3/7 23:27
 */

public class UserDao extends DatabaseDao {

    /**
     * UserDao实例
     */
    private static UserDao instance = null;

    /**
     * 数据查询返回的Cursor
     */
    private Cursor mCursor;

    /**
     * 私有构造方法
     */
    private UserDao() {
        super();
    }

    /**
     * 单利模式:懒汉式，线程不安全
     *
     * @param context 上下文对象
     * @return 返回的UserDao对象
     */
    public static UserDao getInstance(Context context) {
        if (instance == null) {
            instance = (UserDao) DatabaseDao.getInstance(context);
        }
        return instance;
    }

    /**
     * 添加用户
     *
     * @param userEntity 新增数据
     * @return 返回操作结果
     */
    public long insertUser(UserEntity userEntity) {
        LogUtil.i("insertUser");
        if (userEntity == null) {
            return -1L;
        }

        // 插入的值
        ContentValues values = getValues();
        values.put(DatabaseConfig.ID, userEntity.getId());
        values.put(DatabaseConfig.LOGI_NNAME, userEntity.getLoginName());
        values.put(DatabaseConfig.ZH_NAME, userEntity.getZhName());
        values.put(DatabaseConfig.EN_NAME, userEntity.getEnName());
        values.put(DatabaseConfig.SEX, userEntity.getSex());
        values.put(DatabaseConfig.BIRTH, userEntity.getBirth());
        values.put(DatabaseConfig.EMAIL, userEntity.getEmail());
        values.put(DatabaseConfig.PHONE, userEntity.getPhone());
        values.put(DatabaseConfig.ADDRESS, userEntity.getAddress());
        values.put(DatabaseConfig.PASSWORD, userEntity.getPassword());
        values.put(DatabaseConfig.ORDER_BY, userEntity.getOrderBy());
        values.put(DatabaseConfig.CREATE_TIME, userEntity.getCreateTime());
        values.put(DatabaseConfig.UPDATE_TIME, userEntity.getUpdateTime());
        return db.insert(DatabaseConfig.USER_TABLE_NAME, null, values);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public long removeUserById(long id) {
        LogUtil.i("removeUserById");
        return db.delete(DatabaseConfig.USER_TABLE_NAME, DatabaseConfig.ID + " = ", new String[]{String.valueOf(id)});
    }

    /**
     * 更新用户
     *
     * @param userEntity 更新的数据
     */
    public long updateUser(UserEntity userEntity) {
        LogUtil.i("updateUser");
        if (null == userEntity) {
            return -1L;
        }

        // 修改条件
        String whereClause = "id=?";
        // 修改添加参数
        String[] whereArgs = {String.valueOf(userEntity.getId())};

        // 修改的值
        ContentValues values = getValues();
        values.put(DatabaseConfig.ID, userEntity.getId());
        values.put(DatabaseConfig.LOGI_NNAME, userEntity.getLoginName());
        values.put(DatabaseConfig.ZH_NAME, userEntity.getZhName());
        values.put(DatabaseConfig.EN_NAME, userEntity.getEnName());
        values.put(DatabaseConfig.SEX, userEntity.getSex());
        values.put(DatabaseConfig.BIRTH, userEntity.getBirth());
        values.put(DatabaseConfig.EMAIL, userEntity.getEmail());
        values.put(DatabaseConfig.PHONE, userEntity.getPhone());
        values.put(DatabaseConfig.ADDRESS, userEntity.getAddress());
        values.put(DatabaseConfig.PASSWORD, userEntity.getPassword());
        values.put(DatabaseConfig.ORDER_BY, userEntity.getOrderBy());
        values.put(DatabaseConfig.CREATE_TIME, userEntity.getCreateTime());
        values.put(DatabaseConfig.UPDATE_TIME, userEntity.getUpdateTime());
        return db.update(DatabaseConfig.USER_TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * 查询所有用户
     *
     * @return 返回数据
     */
    public List<UserEntity> findAllUser() {
        LogUtil.i("findAllUser");
        List<UserEntity> entities = new ArrayList<>();
        String orderBy = "id desc";

        mCursor = db.query(DatabaseConfig.USER_TABLE_NAME, new String[]{DatabaseConfig.UPDATE_TIME},
                null, null, null, null, orderBy, null);

        try {
            // 获取Cursor中数据
            while (mCursor.moveToNext()) {
                UserEntity entity = new UserEntity();
                entity.setId(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ID)));
                entity.setLoginName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
                entity.setZhName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
                entity.setEnName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EN_NAME)));
                entity.setSex(mCursor.getInt(mCursor.getColumnIndex(DatabaseConfig.SEX)));
                entity.setBirth(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.BIRTH)));
                entity.setEmail(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EMAIL)));
                entity.setPhone(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PHONE)));
                entity.setAddress(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ADDRESS)));
                entity.setPassword(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PASSWORD)));
                entity.setOrderBy(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
                entity.setCreateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
                entity.setUpdateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
                entities.add(entity);
            }
        } finally {
            clearCursor();
        }
        return entities;
    }

    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    分页大小
     * @return 返回数据
     */
    public List<UserEntity> findUserPage(int currentPage, int pageSize) {
        LogUtil.i("findUserPage");
        List<UserEntity> entities = new ArrayList<>();
        String orderBy = "id desc";
        mCursor = db.query(DatabaseConfig.USER_TABLE_NAME, new String[]{DatabaseConfig.UPDATE_TIME},
                null, null, null, null, orderBy, "(" + currentPage + "," + pageSize + ")");

        try {
            // 获取Cursor中数据
            while (mCursor.moveToNext()) {
                UserEntity entity = new UserEntity();
                entity.setId(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ID)));
                entity.setLoginName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
                entity.setZhName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
                entity.setEnName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EN_NAME)));
                entity.setSex(mCursor.getInt(mCursor.getColumnIndex(DatabaseConfig.SEX)));
                entity.setBirth(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.BIRTH)));
                entity.setEmail(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EMAIL)));
                entity.setPhone(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PHONE)));
                entity.setAddress(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ADDRESS)));
                entity.setPassword(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PASSWORD)));
                entity.setOrderBy(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
                entity.setCreateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
                entity.setUpdateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
                entities.add(entity);
            }
        } finally {
            clearCursor();
        }
        return entities;
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public UserEntity findUserById(long id) {
        LogUtil.i("findUserById");
        UserEntity entity = new UserEntity();
        String whereClause = DatabaseConfig.ID + " =?";
        String[] whereArgs = {String.valueOf(id)};
        String orderBy = "id desc";
        mCursor = db.query(DatabaseConfig.USER_TABLE_NAME, null, whereClause, whereArgs, orderBy, null, null);

        try {
            // 获取Cursor中数据
            while (mCursor.moveToNext()) {
                entity.setId(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ID)));
                entity.setLoginName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
                entity.setZhName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
                entity.setEnName(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EN_NAME)));
                entity.setSex(mCursor.getInt(mCursor.getColumnIndex(DatabaseConfig.SEX)));
                entity.setBirth(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.BIRTH)));
                entity.setEmail(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.EMAIL)));
                entity.setPhone(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PHONE)));
                entity.setAddress(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.ADDRESS)));
                entity.setPassword(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.PASSWORD)));
                entity.setOrderBy(mCursor.getLong(mCursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
                entity.setCreateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
                entity.setUpdateTime(mCursor.getString(mCursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
            }
        } finally {
            clearCursor();
        }
        return entity;
    }

    /**
     * 获取ContentValues对象
     *
     * @return
     */
    public ContentValues getValues() {
        return new ContentValues();
    }

    /**
     * 清空用户表
     */
    public void deleteUserTable() {
        try {
            db.execSQL("delete from " + DatabaseConfig.USER_TABLE_NAME);
        } catch (SQLException e) {
            LogUtil.e(e.getMessage());
        } finally {
            db.close();
        }
    }

    /**
     * 删除用户表
     */
    public void dropUserTable() {
        try {
            db.execSQL("drop table " + DatabaseConfig.USER_TABLE_NAME);
        } catch (SQLException e) {
            LogUtil.e(e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 关闭油标
     */
    private void clearCursor() {
        if (mCursor != null) {
            mCursor.close();
        }
        if (db != null) {
            db.close();
        }
    }
}
