package com.sample.android.server;

import android.content.Context;

import com.sample.android.model.entity.UserEntity;

import java.util.List;

public class DateManager {
    public static final String TAG = DateManager.class.getName();
    private static UserDateServer mUserDateServer;
    private static Context context;

    /**
     * 内部类实现单例模式，延迟加载，减少内存开销，一次加载反应不够快
     */
    private static class DateManagerHolder {
        private static DateManager dateManager = new DateManager();
    }

    /**
     * 私有构造函数
     */
    private DateManager() {
    }

    /**
     * 单例模式，返回DateManager对象
     *
     * @return
     */
    public static DateManager getInstance(Context mContext) {
        context = mContext;
        mUserDateServer = UserDateServerImpl.getInstance();
        return DateManagerHolder.dateManager;
    }

    /**
     * 插入用户信息
     *
     * @param userEntity
     * @return
     */
    private long insertUserInfo(UserEntity userEntity) {
        if (userEntity == null) return -1L;
        return mUserDateServer.insertUser(context, userEntity);
    }

    /**
     * 根据id删除指定用户
     *
     * @param id
     * @return
     */
    private void removeAllUser(long id) {
        mUserDateServer.removeAllUser(context);
    }

    /**
     * 根据id删除指定用户
     *
     * @param id
     * @return
     */
    private long removeUserById(long id) {
        return mUserDateServer.removeUserById(context, id);
    }

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    private long updateUser(UserEntity userEntity) {
        if (userEntity == null) return -1L;
        return mUserDateServer.updateUser(context, userEntity);
    }

    /**
     * 清空用户表
     */
    private void deleteUserTable() {
        mUserDateServer.deleteUserTable(context);
    }

    /**
     * 删除用户表
     */
    private void dropUserTable() {
        mUserDateServer.dropUserTable(context);
    }

    /**
     * 根据id查找用户信息
     * @return
     */
    private List<UserEntity> findAllUser() {
        return mUserDateServer.findAllUser(context);
    }

    /**
     * 分页查询用户信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    private List<UserEntity> findUserPage(int currentPage, int pageSize) {
        if (currentPage < 0) currentPage = 1;
        if (pageSize < 0 || pageSize == 0) pageSize = 3;
        return mUserDateServer.findUserPage(context, currentPage, pageSize);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    private UserEntity findUserById(long id) {
        return mUserDateServer.findUserById(context, id);
    }
}
