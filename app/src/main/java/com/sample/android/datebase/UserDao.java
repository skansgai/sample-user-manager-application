package com.sample.android.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sample.android.config.DatabaseConfig;
import com.sample.android.model.entity.UserEntity;
import com.sample.android.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.datebase
 * @功能描述： user数据操作DAO-真正的与数据库交互
 * @作者： 杨松松
 * @创建时间： 2018/3/7 23:27
 */

public class UserDao extends DatabaseDao {

    // UserDao实例
    private static UserDao instance = null;

    // 继承父类的私有构造函数
    private UserDao() {
        super();
    }

    /**
     * 单利模式:懒汉式，线程不安全
     *
     * @param context
     * @return
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
     * @param userEntity
     * @return
     */
    public long insertUser(UserEntity userEntity) {
        LogUtil.i("insertUser");
        if (userEntity == null) {
            return 0;
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
        return this.getDb().insert(DatabaseConfig.TABLE_NAME, null, values);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public long removeUserById(long id) {
        LogUtil.i("removeUserById");
        return this.getDb().delete(DatabaseConfig.TABLE_NAME, DatabaseConfig.ID + " = ", new String[]{String.valueOf(id)});
    }

    /**
     * 更新用户
     *
     * @param userEntity
     */
    public long updateUser(UserEntity userEntity) {
        LogUtil.i("updateUser");
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
        // 修改条件
        String whereClause = "id=?";
        // 修改添加参数
        String[] whereArgs = {String.valueOf(userEntity.getId())};
        return this.getDb().update(DatabaseConfig.TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<UserEntity> findAllUser() {
        LogUtil.i("findAllUser");
        List<UserEntity> entities = new ArrayList<>();
        String orderBy = "id desc";
        Cursor cursor = this.getDb().query(DatabaseConfig.TABLE_NAME, new String[]{DatabaseConfig.UPDATE_TIME},
                null, null, null, null, orderBy, null);

        // 获取Cursor中数据
        while (cursor.moveToNext()) {
            UserEntity entity = new UserEntity();
            entity.setId(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ID)));
            entity.setLoginName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
            entity.setZhName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
            entity.setEnName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EN_NAME)));
            entity.setSex(cursor.getInt(cursor.getColumnIndex(DatabaseConfig.SEX)));
            entity.setBirth(cursor.getString(cursor.getColumnIndex(DatabaseConfig.BIRTH)));
            entity.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EMAIL)));
            entity.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PHONE)));
            entity.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ADDRESS)));
            entity.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PASSWORD)));
            entity.setOrderBy(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
            entity.setCreateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
            entity.setUpdateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
            entities.add(entity);
        }

        if (cursor != null) {
            cursor.close();
        }
        return entities;
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<UserEntity> findUserPage(int currentPage, int pageSize) {
        LogUtil.i("findUserPage");
        List<UserEntity> entities = new ArrayList<>();
        String orderBy = "id desc";
        Cursor cursor = this.getDb().query(DatabaseConfig.TABLE_NAME, new String[]{DatabaseConfig.UPDATE_TIME},
                null, null, null, null, orderBy, "(" + currentPage + "," + pageSize + ")");

        // 获取Cursor中数据
        while (cursor.moveToNext()) {
            UserEntity entity = new UserEntity();
            entity.setId(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ID)));
            entity.setLoginName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
            entity.setZhName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
            entity.setEnName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EN_NAME)));
            entity.setSex(cursor.getInt(cursor.getColumnIndex(DatabaseConfig.SEX)));
            entity.setBirth(cursor.getString(cursor.getColumnIndex(DatabaseConfig.BIRTH)));
            entity.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EMAIL)));
            entity.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PHONE)));
            entity.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ADDRESS)));
            entity.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PASSWORD)));
            entity.setOrderBy(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
            entity.setCreateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
            entity.setUpdateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
            entities.add(entity);
        }

        if (cursor != null) {
            cursor.close();
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
        Cursor cursor = this.getDb().query(DatabaseConfig.TABLE_NAME, null, whereClause, whereArgs, orderBy, null, null);

        // 获取Cursor中数据
        while (cursor.moveToNext()) {
            entity.setId(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ID)));
            entity.setLoginName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.LOGI_NNAME)));
            entity.setZhName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ZH_NAME)));
            entity.setEnName(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EN_NAME)));
            entity.setSex(cursor.getInt(cursor.getColumnIndex(DatabaseConfig.SEX)));
            entity.setBirth(cursor.getString(cursor.getColumnIndex(DatabaseConfig.BIRTH)));
            entity.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseConfig.EMAIL)));
            entity.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PHONE)));
            entity.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseConfig.ADDRESS)));
            entity.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseConfig.PASSWORD)));
            entity.setOrderBy(cursor.getLong(cursor.getColumnIndex(DatabaseConfig.ORDER_BY)));
            entity.setCreateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.CREATE_TIME)));
            entity.setUpdateTime(cursor.getString(cursor.getColumnIndex(DatabaseConfig.UPDATE_TIME)));
        }

        if (cursor != null) {
            cursor.close();
        }
        return entity;
    }

    /**
     * 获取ContentValues对象
     *
     * @return
     */
    private ContentValues getValues() {
        return new ContentValues();
    }

}
