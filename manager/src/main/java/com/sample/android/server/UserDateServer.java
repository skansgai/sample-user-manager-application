package com.sample.android.server;

import android.content.Context;

import com.sample.android.model.entity.UserEntity;

import java.util.List;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： 用户服务接口
 * @作者： 杨松松
 * @创建时间： 2018/3/7 21:52
 */

public interface UserDateServer {

    /**
     * 插入数据
     *
     * @param context    上下文对象
     * @param userEntity user对象
     * @return
     */
    long insertUser(Context context, UserEntity userEntity);

    /**
     * 根据id删除数据
     *
     * @param context 上下文对象
     * @param id
     */
    long removeUserById(Context context, long id);

    /**
     * 清空用户表
     * @param context
     * @return
     */
    void removeAllUser(Context context);

    /**
     * 清空用户表
     * @param context
     * @return
     */
    void deleteUserTable(Context context);

    /**
     * 清空用户表
     * @param context
     * @return
     */
    void dropUserTable(Context context);

    /**
     * 更新数据
     *
     * @param context    上下文对象
     * @param userEntity 返回user对象
     */
    long updateUser(Context context, UserEntity userEntity);

    /**
     * 查询所有记录
     *
     * @param context 上下文对象
     * @return
     */
    List<UserEntity> findAllUser(Context context);

    /**
     * 分页查询
     *
     * @param context     上下文对象
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return
     */
    List<UserEntity> findUserPage(Context context, int currentPage, int pageSize);

    /**
     * 根据id查询
     *
     * @param context 上下文对象
     * @param id      查询的id
     * @return
     */
    UserEntity findUserById(Context context, long id);


}
