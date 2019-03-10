package com.sample.android.server;

import android.content.Context;

import com.sample.android.datebase.DatabaseOpenHelper;
import com.sample.android.datebase.UserDao;
import com.sample.android.model.entity.UserEntity;
import com.sample.android.util.LogUtil;

import java.util.List;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： 用户服务实现类
 * @作者： 杨松松
 * @创建时间： 2018/3/7 23:15
 */

public class UserDateServerImpl implements UserDateServer {

    /**
     * 静态内部类
     */
    private static class UserDataServerHelp {
        private static final UserDateServerImpl INSTANCE = new UserDateServerImpl();
    }

    /**
     * 无参构造器
     */
    private UserDateServerImpl() {
    }

    /**
     * 单利模式-登记式/静态内部类
     *
     * @return
     */
    public static final UserDateServerImpl getInstance() {
        return UserDataServerHelp.INSTANCE;
    }

    /**
     * 插入数据
     *
     * @param context    上下文对象
     * @param userEntity user对象
     * @return
     */
    @Override
    public long insertUser(Context context, UserEntity userEntity) {
        LogUtil.i("insertUser");
        UserDao.getInstance(context).insertUser(userEntity);
        return 0;
    }

    /**
     * 根据id删除数据
     *
     * @param context 上下文对象
     * @param id
     */
    @Override
    public long removeUserById(Context context, long id) {
        LogUtil.i("removeUserById");
        return UserDao.getInstance(context).removeUserById(id);
    }

    /**
     * 清空用户表
     * @param context
     */
    @Override
    public void removeAllUser(Context context) {
        UserDao.getInstance(context).deleteUserTable();
    }

    /**
     * 删除用户表
     * @param context
     */
    @Override
    public void deleteUserTable(Context context) {
        UserDao.getInstance(context).deleteUserTable();
    }

    @Override
    public void dropUserTable(Context context) {
        UserDao.getInstance(context).dropUserTable();
    }

    /**
     * 更新数据
     *
     * @param context    上下文对象
     * @param userEntity 返回user对象
     */
    @Override
    public long updateUser(Context context, UserEntity userEntity) {
        LogUtil.i("updateUser");
        UserEntity userInfo = UserDao.getInstance(context).findUserById(userEntity.getId());
        if (userInfo == null) return -1L;
        return UserDao.getInstance(context).updateUser(userEntity);
    }

    /**
     * 查询所有记录
     *
     * @param context 上下文对象
     * @return
     */
    @Override
    public List<UserEntity> findAllUser(Context context) {
        LogUtil.i("findAllUser");
        return UserDao.getInstance(context).findAllUser();
    }

    /**
     * 分页查询
     *
     * @param context     上下文对象
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    @Override
    public List<UserEntity> findUserPage(Context context, int currentPage, int pageSize) {
        LogUtil.i("findUserPage");
        return UserDao.getInstance(context).findUserPage(currentPage, pageSize);
    }

    /**
     * 根据id查询
     *
     * @param context 上下文对象
     * @param id      查询的id
     * @return
     */
    @Override
    public UserEntity findUserById(Context context, long id) {
        LogUtil.i("findUserById");
        return UserDao.getInstance(context).findUserById(id);
    }

}
